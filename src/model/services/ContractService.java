package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePayment;


	public ContractService(OnlinePaymentService onlinePayment) {
		this.onlinePayment = onlinePayment;
	}

	
	public OnlinePaymentService getOnlinePayment() {
		return onlinePayment;
	}
	public void setOnlinePayment(OnlinePaymentService onlinePayment) {
		this.onlinePayment = onlinePayment;
	}


	public void processContract(Contract contract, Integer months) {
		double basicQuota = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
		LocalDate dueDate = contract.getDate().plusMonths(i);
		
		double interest = onlinePayment.interest(basicQuota, i);
		double fee = onlinePayment.paymentFee(basicQuota + interest);
		
		double amount = basicQuota + interest + fee;
		
		contract.getList().add(new Installment(dueDate, amount));
		}
	}
	
}
