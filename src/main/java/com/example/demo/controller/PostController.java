package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.PostListDto;
import com.example.demo.service.PostFileService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import com.example.demo.vo.Post;
import com.example.demo.vo.PostFile;

@Controller
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@Autowired
	private PostFileService pfs;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/insertPost")
	public ModelAndView insertForm(HttpSession session) {
		ModelAndView mav=new ModelAndView("insertPost");
		System.out.println("글쓰기 폼 왔다");
		if(session.getAttribute("userNum")==null) {
			mav.setViewName("login");
		}
		return mav;
	}
	
	@PostMapping("/insertPost")
	public String insertOK(Post post, PostFile postFile, HttpSession session, @RequestParam(value="multiFile",required = false) MultipartFile[] multiFileList, HttpServletRequest request){
		System.out.println("글쓰기ok왔다");
		System.out.println("지금 로그인한 사용자 번호"+(Integer)(session.getAttribute("userNum")));
		post.setPostNum(ps.getNextPostNum());
		post.setUserNum((Integer)(session.getAttribute("userNum")));
		ps.insertPost(post);
		if(multiFileList!=null) {	
			// 받아온것 출력 확인
			System.out.println("multiFileList : " + multiFileList);
			
			// path 가져오기
			String path = request.getSession().getServletContext().getRealPath("resources");
			String root = "/Users/feelj/boardImage";
			
			List<Map<String, String>> fileList = new ArrayList<>();
			
			for(int i = 0; i < multiFileList.length; i++) {
				String originFile = multiFileList[i].getOriginalFilename();
				String ext = originFile.substring(originFile.lastIndexOf("."));
				String changeFile = UUID.randomUUID().toString() + ext;
				
				Map<String, String> map = new HashMap<>();
				map.put("originFile", originFile);
				map.put("changeFile", changeFile);
				postFile.setFileName(originFile);
				postFile.setSavedFileName(changeFile);
				postFile.setFilePathUrl(path);
				postFile.setPostNum(post.getPostNum());
				postFile.setPostFileNum(pfs.getNextPostNum());
				pfs.insertPostFile(postFile);
				
				fileList.add(map);
			}
			
			System.out.println(fileList);
			
			// 파일업로드
			try {
				for(int i = 0; i < multiFileList.length; i++) {
					File uploadFile = new File(root + "\\" + fileList.get(i).get("changeFile"));
					multiFileList[i].transferTo(uploadFile);
				}
				
				System.out.println("다중 파일 업로드 성공!");
				
			} catch (IllegalStateException | IOException e) {
				System.out.println("다중 파일 업로드 실패 ㅠㅠ");
				// 만약 업로드 실패하면 파일 삭제
				for(int i = 0; i < multiFileList.length; i++) {
					new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
				}
				e.printStackTrace();
			}
		}
		return "redirect:/detailPost/"+post.getPostNum();
	}
	
	@GetMapping("/detailPost/{postNum}")
	public ModelAndView detailPost(@PathVariable int postNum, Model model, HttpSession session) {
		ModelAndView mav=new ModelAndView("detailPost");
		model.addAttribute("post", ps.detailPost(postNum));
		model.addAttribute("user", us.findByUserNum(ps.detailPost(postNum).getUserNum()));
//		if(session.getAttribute("userNum")==null) {
//			mav.setViewName("login");
//			if(session.getAttribute("userNum")!=null) {
//				mav.setViewName("detailPost");
//			}
//		}
		model.addAttribute("postFileList", pfs.detailPostFile(postNum));
		ps.plusPostHit(postNum);
		return mav;
	}
	
	@GetMapping("/updatePost/{postNum}")
	public ModelAndView updatePostForm(@PathVariable int postNum, Model model) {
		ModelAndView mav=new ModelAndView("updatePost");
		model.addAttribute("post", ps.detailPost(postNum));
		model.addAttribute("postNum", ps.detailPost(postNum).getPostNum());
		model.addAttribute("postFileList", pfs.detailPostFile(postNum));
		return mav;
	}
	
	@PostMapping("/updatePost/{postNum}")
	public String updatePostOK(@PathVariable int postNum, Post post, PostFile postFile, @RequestParam(value="multiFile",required = false) MultipartFile[] multiFileList, HttpServletRequest request) {
		System.out.println("업데이트ok컨트롤러 옴");
		if(multiFileList!=null) {	
			// 받아온것 출력 확인
			System.out.println("multiFileList : " + multiFileList);
			
			// path 가져오기
			String path = request.getSession().getServletContext().getRealPath("resources");
			String root = "/Users/feelj/boardImage";
			
			List<Map<String, String>> fileList = new ArrayList<>();
			
			for(int i = 0; i < multiFileList.length; i++) {
				String originFile = multiFileList[i].getOriginalFilename();
				String ext = originFile.substring(originFile.lastIndexOf("."));
				String changeFile = UUID.randomUUID().toString() + ext;
				
				Map<String, String> map = new HashMap<>();
				map.put("originFile", originFile);
				map.put("changeFile", changeFile);
				postFile.setFileName(originFile);
				postFile.setSavedFileName(changeFile);
				postFile.setFilePathUrl(path);
				postFile.setPostNum(post.getPostNum());
				postFile.setPostFileNum(pfs.getNextPostNum());
				pfs.insertPostFile(postFile);
				
				fileList.add(map);
			}
			
			System.out.println(fileList);
			
			// 파일업로드
			try {
				for(int i = 0; i < multiFileList.length; i++) {
					File uploadFile = new File(root + "\\" + fileList.get(i).get("changeFile"));
					multiFileList[i].transferTo(uploadFile);
				}
				
				System.out.println("다중 파일 업로드 성공!");
				
			} catch (IllegalStateException | IOException e) {
				System.out.println("다중 파일 업로드 실패 ㅠㅠ");
				// 만약 업로드 실패하면 파일 삭제
				for(int i = 0; i < multiFileList.length; i++) {
					new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
				}
				e.printStackTrace();
			}
		}
		ps.updatePost(post);
		return "redirect:/detailPost/"+post.getPostNum();
	}
	
	@GetMapping("/deletePost/{postNum}")
	public String deletePostOK(@PathVariable int postNum) {
		String root = "/Users/feelj/boardImage";
		if(pfs.findByPostNum(postNum)!=null) {
			pfs.deletePostFileAll(postNum);
			
			for(PostFile pf:pfs.findByPostNum(postNum)) {
				new File(root + "\\" + pf.getSavedFileName()).delete();
			}
		}
		ps.deletePost(postNum);
		return "redirect:/main";
	}
	
	@GetMapping("/board/{boardNum}")
	public ModelAndView goBoard(Model model, @PathVariable int boardNum) {
		ModelAndView mav=new ModelAndView("board");
		List<PostListDto> postList=new ArrayList();
		if(ps.findByBoardNum(boardNum)!=null) {
			for(int i=0;i<ps.findByBoardNum(boardNum).size();i++) {
				PostListDto pl=new PostListDto();
				pl.setPostNum(ps.findByBoardNum(boardNum).get(i).getPostNum());
				pl.setPostTitle(ps.findByBoardNum(boardNum).get(i).getPostTitle());
				pl.setUserNick(us.findByUserNum(ps.findByBoardNum(boardNum).get(i).getUserNum()).getUserNick());
				postList.add(pl);
			}
		}
		model.addAttribute("postList",postList);
		return mav;
	}
}
