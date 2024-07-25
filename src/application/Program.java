package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Data (dd/mm/aaaa): ");
		LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Valor do contrato: ");
		double amount = sc.nextDouble();		
		System.out.print("Entre com o numero de parcelas: ");
		int month = sc.nextInt();
		
		Contract contract = new Contract(number, date, amount);
		
		ContractService service = new ContractService(new PaypalService());
		
		
		service.processContract(contract, month);
		System.out.println("PARCELAS: ");
		for (Installment list : contract.getList()) {
			System.out.println(list);
		}
		
		
		sc.close();
	}

}
