package kr.kamgaru.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kamgaru.Action.Action;
import kr.kamgaru.Action.ActionForward;
import kr.kamgaru.service.Activity.ActivityBoardTeam;
import kr.kamgaru.service.Activity.ActivityContentAction;
import kr.kamgaru.service.Activity.ActivityListAction;
import kr.kamgaru.service.Activity.ActivityReplyAction;
import kr.kamgaru.service.Activity.ActivityReplyDeleteAction;
import kr.kamgaru.service.Activity.ActivityTeamContent;
import kr.kamgaru.service.Admin.AdminBoardDelete;
import kr.kamgaru.service.Admin.AdminListAction;
import kr.kamgaru.service.Admin.AdminManagerDelete;
import kr.kamgaru.service.Admin.AdminMemberManage;
import kr.kamgaru.service.Admin.AdminRequestOk;
import kr.kamgaru.service.Admin.AdminWriteAction;
import kr.kamgaru.service.Admin.WriteRequestConFirm;
import kr.kamgaru.service.Community.ComDeleteAction;
import kr.kamgaru.service.Community.ComListAction;
import kr.kamgaru.service.Community.ComReplyAction;
import kr.kamgaru.service.Community.ComReplyDeleteAction;
import kr.kamgaru.service.Community.ComSearchAction;
import kr.kamgaru.service.Community.ComSubListAction;
import kr.kamgaru.service.Community.ComSubViewAction;
import kr.kamgaru.service.Community.ComSubWriteAction;
import kr.kamgaru.service.Community.ComWriteAction;
import kr.kamgaru.service.Contest.ContestBoardReplyDelete;
import kr.kamgaru.service.Contest.ContestBoardTeam;
import kr.kamgaru.service.Contest.ContestBoardViewAction;
import kr.kamgaru.service.Contest.ContestListAction;
import kr.kamgaru.service.Contest.ContestReplyAction;
import kr.kamgaru.service.Group.GroupBoardLike;
import kr.kamgaru.service.Group.GroupBoardLikeCheck;
import kr.kamgaru.service.Group.GroupBoardRankList;
import kr.kamgaru.service.Group.GroupBoardViewAction;
import kr.kamgaru.service.Group.GroupListAction;
import kr.kamgaru.service.Group.GroupReplyAction;
import kr.kamgaru.service.Group.GroupReplyDeleteAction;

import org.json.simple.JSONObject;

@WebServlet("*.kam")
public class KamgaruController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KamgaruController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		
		JSONObject jsonObject = new JSONObject();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmdURI = requestURI.substring(contextPath.length());
		
		ActionForward forward = new ActionForward();
		Action action;
		
		System.out.println("controller-OK : " + cmdURI);

		
		/*******************************관리자 시작*************************************/
		/* 글 작성 화면 요청이 들어왔을때, ok */
		if (cmdURI.equals("/Common/writeok.kam")) {

			action = new AdminWriteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		/* 관리자-목록조회 */
		} else if (cmdURI.equals("/Common/AdminListAction.kam")) {
		
			action = new AdminListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (cmdURI.equals("/Common/AdminRequestOk.kam")) {
		
			action = new AdminRequestOk();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (cmdURI.equals("/Common/writeRequestConfirm.kam")) {
		
			action = new WriteRequestConFirm();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* 관리자-회원목록조회 */
		}else if (cmdURI.equals("/Common/AdminMemberList.kam")) {
		
			action = new AdminMemberManage();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* 관리자-회원삭제 */
		}else if (cmdURI.equals("/Common/AdminManageDelete.kam")) {
			
			System.out.println("***AdminManagerDelete-TEST");
			action = new AdminManagerDelete();
			System.out.println("***AdminManagerDelete-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* 관리자-게시글삭제 */
		} else if(cmdURI.equals("/Common/AdminBoardDelete.kam")) {
			System.out.println("***AdminBoardDelete-TEST");
			action = new AdminBoardDelete();
			System.out.println("***AdminBoardDelete-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		/*******************************관리자 끝*************************************/
			
			
		/*******************************공모전 시작*************************************/	
			/* 공모전-목록보기 */
		} else if(cmdURI.equals("/Common/ContestListAction.kam")) {
			System.out.println("***ContestList-TEST");
			action = new ContestListAction();
			System.out.println("***ContestList-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* 공모전-상세보기*/
		}else if(cmdURI.equals("/Common/ContestBoardViewAction.kam")) {
			System.out.println("***ContestList-TEST");
			action = new ContestBoardViewAction();
			System.out.println("***ContestList-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(cmdURI.equals("/Common/ContestBoardTeam.kam")) {
			System.out.println("***ContestBoardTeam-TEST");
			action = new ContestBoardTeam();
			System.out.println("***ContestBoardTeam-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(cmdURI.equals("/Common/ContestReplyAction.kam")) {
			System.out.println("***ContestReplyAction-TEST");
			action = new ContestReplyAction();
			System.out.println("***ContestReplyAction-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(cmdURI.equals("/Common/ContestBoardReplyDelete.kam")) {
			System.out.println("***ContestBoardReplyDelete-TEST");
			action = new ContestBoardReplyDelete();
			System.out.println("***ContestBoardReplyDelete-OK");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*******************************공모전 끝*************************************/
		
		/********************************지은 시작********************************/
		//글 작성 버튼을 눌렀을때!!!!!!!!!
		else if(cmdURI.equals("/Community/comwrite.kam")){
        	forward.setRedirect(false);
        	forward.setPath("/Community/CommunityWrite.jsp");
        }
        
        // 글 작성 요청, ok
        else if(cmdURI.equals("/Community/comwriteok.kam")){
        	action = new ComWriteAction();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        
        // 글 리스트 보여주기, ok
        else if(cmdURI.equals("/Community/comlist.kam")){
        	action = new ComListAction();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        //서브 글 작성
        else if(cmdURI.equals("/Community/subwrite.kam")){
        	forward.setRedirect(false);
        	forward.setPath("/Community/CommunitySubWrite.jsp");
        }
		// 서브 글 작성 요청, ok
        else if(cmdURI.equals("/Community/subwriteok.kam")){
        	action = new ComSubWriteAction();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        // 글 서브 리스트보여주기, ok
        else if(cmdURI.equals("/Community/sublist.kam")){
        	System.out.println("sublist.kam으로 가라");
        	action = new ComSubListAction();
        	System.out.println("sublist.kam으로 갔어??");
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        // 글 서브 내용 보여주기, ok
        else if(cmdURI.equals("/Community/subview.kam")){
        	System.out.println("subview.kam으로 가라");
        	action = new ComSubViewAction();
        	System.out.println("subview.kam으로 갔어??");
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
		// 글 검색 리스트 보여주기
        else if(cmdURI.equals("/Community/search.kam")){
        	System.out.println("search.kam으로 가라");
        	action = new ComSearchAction();
        	System.out.println("search.kam으로 갔어??");
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
		
        
        
        /*// 글 수정 화면 제공, ok
        else if(cmdURI.equals("/boardEditForm.bbs")){
        	action = new BoardEditAction();
        	
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        
        // 글 수정 처리, ok
        else  if(cmdURI.equals("/boardEdit.bbs")){
        	action = new BoardEditOkAction();
        	
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        // 글 삭제 비밀번호 확인 화면 제공, ok
        else if(cmdURI.equals("/boardDeletePassword.bbs")){
        	forward.setRedirect(false);
        	forward.setPath("/board/board_delete.jsp");
        }*/
        
        
        // 커뮤니티글 삭제, ok
        else if(cmdURI.equals("/Community/comDeleteok.kam")){
        	action = new ComDeleteAction();
        	
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        // 댓글 작성
        else if(cmdURI.equals("/Community/comReplyok.kam")) {
        	System.out.println("댓글 넘어오냐");
        	action = new ComReplyAction();
        	
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		// 커뮤니티 내 댓글 삭제
		else if (cmdURI.equals("/Community/comReplyDelete.kam")) {
			action = new ComReplyDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		/*************************커뮤니티 끝********************************/
		
		
		
		/*************************대외활동 시작********************************/
        // 글 리스트 보여주기, ok  ActivityBoardList.jsp
        else if(cmdURI.equals("/ActivityBoard/activityboardlist.kam")){
        	action = new ActivityListAction();
        	
        	try {
				forward = action.execute(request, response);
				
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        //상세보기 ActivityBoardView.jsp 
        else if(cmdURI.equals("/ActivityBoard/activityboardcontent.kam")){
        	action = new ActivityContentAction();
        	
        	try {
				forward = action.execute(request, response);
				
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        //리플
        else if(cmdURI.equals("/ActivityBoard/activityReplyok.kam")){
        	try {
        		action = new ActivityReplyAction();
				forward = action.execute(request, response);
				
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
		
		//팀원모집  ActivityBoardSubList.jsp
        else if(cmdURI.equals("/ActivityBoard/activityBoardTeam.kam")){
        	
        	try {
        		action = new ActivityBoardTeam();
				forward = action.execute(request, response);
				
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
		
		// 대외활동 상세보기 내 댓글 작성 ActivityBoardView.jsp
		else if (cmdURI.equals("/ActivityBoard/activityBoardReplyok.kam")) {
			action = new ActivityReplyAction();

			try {
			forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
						}
					}
		
		
		// 대외활동 상세보기 내 댓글 삭제
		else if (cmdURI.equals("/ActivityBoard/activityBoardReplyDelete.kam")) {
			action = new ActivityReplyDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	
		else if (cmdURI.equals("/ActivityBoard/activityTeamContent.kam")) {
			action = new ActivityTeamContent();

			try {
			forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
						}
					}
		
		/*************************대외활동 끝**********************************/
		
		
		
		
		
		/*************************동아리 시작**********************************/
		// 
				else if (cmdURI.equals("/GroupBoard/GroupBoardList.kam")) {
					action = new GroupListAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				else if (cmdURI.equals("/GroupBoard/GroupBoardView.kam")) {
					action = new GroupBoardViewAction();

					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			
					else if (cmdURI.equals("/GroupBoard/GroupBoardReplyok.kam")) {
						action = new GroupReplyAction();

						try {
							forward = action.execute(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				
				else if (cmdURI.equals("/GroupBoard/GroupBoardReplyDelete.kam")) {
					action = new GroupReplyDeleteAction();

					try {
						forward = action.execute(request, response);
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
				
				 // ok
			      else if (cmdURI.equals("/GroupBoard/GroupBoardRankList.kam")) {
			      
			         action = new GroupBoardRankList();
			       
			         try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
			      }
		
				
			      else if (cmdURI.equals("/GroupBoard/GroupBoardLike.kam")) {
			         action = new GroupBoardLike();
			         try {
			            forward = action.execute(request, response);
			            String result = request.getAttribute("result").toString();
			            jsonObject.put("result", Integer.parseInt(result));
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
			      }
		
				
			      else if (cmdURI.equals("/GroupBoard/GroupBoardLikeCheck.kam")) {
			         action = new GroupBoardLikeCheck();
			         try {
			            forward = action.execute(request, response);
			            String likecount = request.getAttribute("likecount").toString();
			            jsonObject.put("likecount", Integer.parseInt(likecount));
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
			      }
		
				
		/*************************동아리 끝 ************************************/
		
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward
						.getPath());
				if(dis == null){
					response.setContentType("application/x-json; charset=UTF-8");
					response.getWriter().print(jsonObject);
				}else{
					dis.forward(request, response);
				}
				
			}
		}
	}
}