package entity;

public class UserBean {
	private String id;
	private BankAccountBean bankAccount;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BankAccountBean getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccountBean bankAccount) {
		this.bankAccount = bankAccount;
	}
}
