

// Restcontroller 방식으로 view를 거치지 않습니다.
// 아래 블록 단위의 주석은 views의 jsp들을 호출하는 코드로
// 남겨둡니다.

package com.example.demo.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.service.BoardService;

@RestController
public class BoardController {

	@Resource(name = "com.example.demo.board.service.BoardService")
	BoardService mBoardService;

	@RequestMapping("/list") // 게시판 리스트 화면 호출
	private List<BoardVO> boardList() throws Exception {

		List<BoardVO> list = mBoardService.boardListService();
		return list;

	}

	@RequestMapping("/detail/{bno}")
	private BoardVO boardDetail(@PathVariable int bno) throws Exception {

		BoardVO detail = mBoardService.boardDetailService(bno);

		return detail;
	}

//	@RequestMapping("/insert") // 게시글 작성폼 호출
//	private String boardInsertForm() {
//
//		return "insert";
//	}

	@RequestMapping("/insert")
	private BoardVO boardInsertProc(HttpServletRequest request) throws Exception {

		BoardVO board = new BoardVO();

		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));

		mBoardService.boardInsertService(board);

		return board;
	}

//	@RequestMapping("/update/{bno}") // 게시글 수정폼 호출
//	private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
//
//		model.addAttribute("detail", mBoardService.boardDetailService(bno));
//
//		return "update";
//	}

	@RequestMapping("/update/{bno}")
	private BoardVO boardUpdateProc(@PathVariable int bno, HttpServletRequest request) throws Exception {

		BoardVO board = new BoardVO();
		
		board.setBno(bno);
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));

		mBoardService.boardUpdateService(board);
		
		// update된 board 내용 출력
		return mBoardService.boardDetailService(bno);
	}

	// 삭제 이후에 list로 데이터 호출
	@RequestMapping("/delete/{bno}")
	private List<BoardVO> boardDelete(@PathVariable int bno) throws Exception {

		mBoardService.boardDeleteService(bno);

		List<BoardVO> list = mBoardService.boardListService();
		
		return list;
	}
}

/*
 * 밑의 코드는 RestController 방식이 아닌 views를 통한 jsp 호출 code입니다. 아래 주석은 Test를 위해서
 * 남겨둡니다.
 */

//package com.example.demo.board.controller;
// 
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
// 
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
// 
//import com.example.demo.board.domain.BoardVO;
//import com.example.demo.board.service.BoardService;
// 
//@Controller
//public class BoardController {
// 
//    @Resource(name="com.example.demo.board.service.BoardService")
//    BoardService mBoardService;
//    
//    @RequestMapping("/list") //게시판 리스트 화면 호출  
//    private String boardList(Model model) throws Exception{
//        
//        model.addAttribute("list", mBoardService.boardListService());
//        
//        return "list"; //생성할 jsp
//    }
//    
//    @RequestMapping("/detail/{bno}") 
//    private String boardDetail(@PathVariable int bno, Model model) throws Exception{
//        
//        model.addAttribute("detail", mBoardService.boardDetailService(bno));
//        
//        return "detail";
//    }
//    
//    @RequestMapping("/insert") //게시글 작성폼 호출  
//    private String boardInsertForm(){
//        
//        return "insert";
//    }
//    
//    @RequestMapping("/insertProc")
//    private int boardInsertProc(HttpServletRequest request) throws Exception{
//        
//        BoardVO board = (BoardVO) request.getParameterMap();
//        
//        return mBoardService.boardInsertService(board);
//    }
//    
//    @RequestMapping("/update/{bno}") //게시글 수정폼 호출  
//    private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception{
//        
//        model.addAttribute("detail", mBoardService.boardDetailService(bno));
//        
//        return "update";
//    }
//    
//    @RequestMapping("/updateProc")
//    private int boardUpdateProc(HttpServletRequest request) throws Exception{
//        
//        BoardVO board = (BoardVO) request.getParameterMap();
//        
//        return mBoardService.boardUpdateService(board);
//    }
// 
//    @RequestMapping("/delete/{bno}")
//    private String boardDelete(@PathVariable int bno) throws Exception{
//        
//        mBoardService.boardDeleteService(bno);
//        
//        return "redirect:/list";
//    }
//}