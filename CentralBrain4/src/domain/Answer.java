package domain;

import domain.value.AdviceDetail;
import domain.value.AnswerDetail;
import domain.value.AnswerID;
import domain.value.BestAnswerFlag;
import domain.value.InvisiblePeriod;
import domain.value.NumberOfGood;
import domain.value.PostDate;
import domain.value.QuestionID;
import domain.value.UserID;

public class Answer {
	private AnswerID ANSWERID;
	private QuestionID QUESTIONID;
	private NumberOfGood NUMBEROFGOOD;
	private BestAnswerFlag BESTANSWERFLAG;
	private AnswerDetail ANSWERDETAIL;
	private InvisiblePeriod INVISIBLEPERIOD;
	private AdviceDetail ADVICEDETAIL;
	private PostDate POSTDATE;
	private UserID USERID;

	public AnswerID getANSWERID() {
		return ANSWERID;
	}
	public void setANSWERID(AnswerID aNSWERID) {
		ANSWERID = aNSWERID;
	}
	public QuestionID getQUESTIONID() {
		return QUESTIONID;
	}
	public void setQUESTIONID(QuestionID qUESTIONID) {
		QUESTIONID = qUESTIONID;
	}
	public NumberOfGood getNUMBEROFGOOD() {
		return NUMBEROFGOOD;
	}
	public void setNUMBEROFGOOD(NumberOfGood nUMBEROFGOOD) {
		NUMBEROFGOOD = nUMBEROFGOOD;
	}
	public BestAnswerFlag getBESTANSWERFLAG() {
		return BESTANSWERFLAG;
	}
	public void setBESTANSWERFLAG(BestAnswerFlag bESTANSWERFLAG) {
		BESTANSWERFLAG = bESTANSWERFLAG;
	}
	public AnswerDetail getANSWERDETAIL() {
		return ANSWERDETAIL;
	}
	public void setANSWERDETAIL(AnswerDetail aNSWERDETAIL) {
		ANSWERDETAIL = aNSWERDETAIL;
	}
	public InvisiblePeriod getINVISIBLEPERIOD() {
		return INVISIBLEPERIOD;
	}
	public void setINVISIBLEPERIOD(InvisiblePeriod iNVISIBLEPERIOD) {
		INVISIBLEPERIOD = iNVISIBLEPERIOD;
	}
	public AdviceDetail getADVICEDETAIL() {
		return ADVICEDETAIL;
	}
	public void setADVICEDETAIL(AdviceDetail aDVICEDETAIL) {
		ADVICEDETAIL = aDVICEDETAIL;
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
	public AnswerID getAnswerID() {
		return ANSWERID;
	}
	public void setAnswerID(AnswerID aNSWERID) {
		ANSWERID = aNSWERID;
	}
	public QuestionID getQuestionID() {
		return QUESTIONID;
	}
	public void setQuestionID(QuestionID qUESTIONID) {
		QUESTIONID = qUESTIONID;
	}
	public NumberOfGood getNumberOfGood() {
		return NUMBEROFGOOD;
	}
	public void setNumberOfGood(NumberOfGood nUMBEROFGOOD) {
		NUMBEROFGOOD = nUMBEROFGOOD;
	}
	public BestAnswerFlag getBestAnswerFlag() {
		return BESTANSWERFLAG;
	}
	public void setBestAnswerFlag(BestAnswerFlag bESTANSWERFLAG) {
		BESTANSWERFLAG = bESTANSWERFLAG;
	}
	public AnswerDetail getAnswerDetail() {
		return ANSWERDETAIL;
	}
	public void setAnswerDetail(AnswerDetail aNSWERDETAIL) {
		ANSWERDETAIL = aNSWERDETAIL;
	}

	public AdviceDetail getAdviceDetail() {
		return ADVICEDETAIL;
	}
	public void setAdviceDetail(AdviceDetail aDVICEDETAIL) {
		ADVICEDETAIL = aDVICEDETAIL;
	}
	public PostDate getPostDate() {
		return POSTDATE;
	}
	public void setPostDate(PostDate pOSTDATE) {
		POSTDATE = pOSTDATE;
	}
	public UserID getUserID() {
		return USERID;
	}
	public void setUserID(UserID uSERID) {
		USERID = uSERID;
	}


}
