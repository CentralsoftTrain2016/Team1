package domain;

import domain.value.AnswerFlag;
import domain.value.BestAnswerID;
import domain.value.PostDate;
import domain.value.QuestionDetail;
import domain.value.QuestionID;
import domain.value.UserID;

public class Question {
	private	QuestionID	QUESTIONID	;
	private	QuestionDetail	QUESTIONDETAIL	;
	private	BestAnswerID	BESTANSWERID	;
	private	PostDate	POSTDATE	;
	private	UserID	USERID	;
	private	AnswerFlag	ANSWERFLAG	;
	public QuestionID getQUESTIONID() {
		return QUESTIONID;
	}
	public String getCutQUESTIONDETAIL(){
		if(QUESTIONDETAIL.get().length()>30){
		return	QUESTIONDETAIL.get().substring(0,30);
		}
		return 	QUESTIONDETAIL.get();
	}
	public void setQUESTIONID(QuestionID qUESTIONID) {
		QUESTIONID = qUESTIONID;
	}
	public QuestionID getQUESTIONID(QuestionID qUESTIONID) {
		return QUESTIONID;
	}
	public QuestionDetail getQUESTIONDETAIL() {
		return QUESTIONDETAIL;
	}
	public void setQUESTIONDETAIL(QuestionDetail qUESTIONDETAIL) {
		QUESTIONDETAIL = qUESTIONDETAIL;
	}
	public BestAnswerID getBESTANSWERID() {
		return BESTANSWERID;
	}
	public void setBESTANSWERID(BestAnswerID bESTANSWERID) {
		BESTANSWERID = bESTANSWERID;
	}
	public PostDate getPOSTDATE() {
		return POSTDATE;
	}
	public void setPOSTDATE(PostDate pOSTDATE) {
		POSTDATE = pOSTDATE;
	}
	public UserID getUSERID() {
		return USERID;
	}
	public void setUSERID(UserID uSERID) {
		USERID = uSERID;
	}
	public AnswerFlag getANSWERFLAG() {
		return ANSWERFLAG;
	}
	public void setANSWERFLAG(AnswerFlag aNSWERFLAG) {
		ANSWERFLAG = aNSWERFLAG;
	}


}
