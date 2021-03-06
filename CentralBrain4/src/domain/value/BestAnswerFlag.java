package domain.value;

public class BestAnswerFlag extends Value {
	private boolean value;

	public BestAnswerFlag(boolean value) {
		super();
		this.value = value;
	}

	public BestAnswerFlag(int value){
		super();
		if(value == 1){
			this.value = true;
		}
		else if(value == 0){
			this.value = false;
		}
		else{
			throw new RuntimeException("AnswerFlagの値が不正です。");
		}
	}
	public boolean get() {
		return value;
	}
}