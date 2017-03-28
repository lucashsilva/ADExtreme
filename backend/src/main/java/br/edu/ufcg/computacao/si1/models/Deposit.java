package br.edu.ufcg.computacao.si1.models;

public class Deposit {
	private Long amount;
	
	public Deposit(Long value) {
		this.amount = value;
	}
	
	public Deposit() {
		
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	

}
