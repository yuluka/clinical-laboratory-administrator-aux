package main;

import java.util.Scanner;

import model.LaboratoryAdministrator;

public class Main {

	private static Scanner in = new Scanner(System.in);
	
	private static LaboratoryAdministrator labAdministrator = new LaboratoryAdministrator();
	
	public static void main(String[] args) {
		System.out.println("�Bienvenido!");
		
		labAdministrator.loadData();
		
		menu();
	}
	
	public static void menu() {
		System.out.println("\n----- Menu -----\n"
				+ "Selecciona qu� deseas hacer:\n"
				+ "1) Ver lista de personas en el laboratorio.\n"
				+ "2) Ver el orden de atenci�n de las unidades.\n"
				+ "3) Hacer ingreso de paciente.\n"
				+ "4) Hacer egreso de paciente.\n"
				+ "5) Ver todos los pacientes en el sistema.\n"
				+ "6) Enviar paciente al laboratorio.\n"
				+ "7) Buscar paciente.\n"
				+ "8) Deshacer acci�n.\n"
				+ "0) Salir.");
		
		int selection = Integer.parseInt(in.nextLine());
		
		switch (selection) {
		case 1:
			seePatientsInLab();
			break;

		case 2:
			seeAtentionOrder();
			break;

		case 3:
			registerPatient();
			break;

		case 4:
			egressPatient();
			break;

		case 5:
			seeAllPatients();
			break;

		case 6:
			sendPatientToLab();
			break;
			
		case 7:
			searchPatient();
			break;
			
		case 8:
			undoAction();
			break;
			
		case 0:
			System.out.println("\n�Adi�s!");
			System.exit(0);
			break;
			
		default:
			System.out.println("\nLa opci�n que elegiste no es v�lida. Intenta otra vez.");
			menu();
			break;
		}
	}
	
	public static void seePatientsInLab() {
		System.out.println("\n----- Ver lista de personas en el laboratorio -----\n");
		
		System.out.println(labAdministrator.displayAllPatientsInLab());
		
		menu();
	}
	
	public static void seeAtentionOrder() {
		System.out.println("\n----- Ver el orden de atenci�n de las unidades -----\n");
		System.out.println("�De qu� unidad desea ver el orden de atenci�n?"
				+ "\n1) Prop�sito general."
				+ "\n2) Hematolog�a.");
		int selection = Integer.parseInt(in.nextLine());
		
		switch (selection) {
		case 1:
			System.out.println("\nOrden de atenci�n de la unidad de prop�sito general:"
					+ "\nFila prioritaria:\n" + labAdministrator.displayGeneralPriorityPatients()
					+ "\nFila normal:\n" + labAdministrator.displayGeneralNonPriorityPatients());
			break;
			
		case 2:
			System.out.println("\nOrden de atenci�n de la unidad de hematolog�a:"
					+ "\nFila prioritaria:\n" + labAdministrator.displayHematologyPriorityPatients()
					+ "\nFila normal:\n" + labAdministrator.displayHematologyNonPriorityPatients());
			break;

		default:
			System.out.println("\nLa opci�n que elegiste no es v�lida. Intenta otra vez.");
			seeAtentionOrder();
			break;
		}
		
		menu();
	}
	
	public static void registerPatient() {
		System.out.println("\n----- Hacer ingreso de paciente -----\n");
		
		System.out.println("Ingrese la informaci�n del paciente que desea registrar."
				+ "\nNombre completo:");
		String name = in.nextLine();
		
		System.out.println("\nN�mero de identificaci�n:");
		String id = in.nextLine();
		
		if(labAdministrator.searchPatient(id) != null) {
			System.out.println("\nEl paciente que desea registrar ya se encuentra registrado.");
			menu();
		}
		
		System.out.println("\nEdad (a�os):");
		String age = in.nextLine();
		
		System.out.println("\nN�mero de tel�fono:");
		String celNumber = in.nextLine();
		
		System.out.println("\nDirecci�n:");
		String address = in.nextLine();
		
		System.out.println("\n�Tiene prioridad?"
				+ "\n1) No."
				+ "\n2) S�.");
		int prioritySelect = Integer.parseInt(in.nextLine());
		
		while(prioritySelect > 2 || prioritySelect < 1) {
			System.out.println("\nSelecci�n inv�lida. Vuelve a intentar:");
			prioritySelect = Integer.parseInt(in.nextLine());
		}
		
		System.out.println("\n�A qu� unidad se dirige?"
				+ "\n1) Prop�sito general."
				+ "\n2) Hematolog�a.");
		int unitSelect = Integer.parseInt(in.nextLine());
		
		while(unitSelect > 2 || unitSelect < 1) {
			System.out.println("\nSelecci�n inv�lida. Vuelve a intentar:");
			unitSelect = Integer.parseInt(in.nextLine());
		}
		
		boolean priority = false;
		if(prioritySelect == 2) {
			priority = true;
		}
		
		boolean unit = false;
		if(unitSelect == 2) {
			unit = true;
		}
		
		int priorityValue = 0;
		if(priority) {
			System.out.println("\nIngrese la prioridad que posee la persona "
					+ "(est� ordenado de menor a mayor):"
					+ "\n1) Discapacidad."
					+ "\n2) Embarazo."
					+ "\n3) Adulto mayor.");
			priorityValue = Integer.parseInt(in.nextLine());
			
			while (priorityValue < 1 || priorityValue > 3) {
				System.out.println("\nSelecci�n inv�lida. Vuelve a intentar:");
				
				priorityValue = Integer.parseInt(in.nextLine());
			}
		}
		
		if(labAdministrator.addPatient(name, priority, id, age, celNumber, address, unit, 
				priorityValue)) {
			System.out.println("\n�Paciente ingresado exitosamente!");
		} else {
			System.out.println("\nOcurri� un error en el ingreso del paciente.");
		}
		
		menu();	
	}
	
	public static void egressPatient() {
		System.out.println("\n----- Hacer egreso de paciente -----\n");
		
		menu();	
	}
	
	public static void undoAction() {
		System.out.println("\n----- Deshacer acci�n -----\n");
		
		menu();	
	}
	
	public static void seeAllPatients() {
		System.out.println("\n----- Ver todos los pacientes en el sistema -----\n");
		System.out.println("Los pacientes en el sistema son: "
				+ "\n" + labAdministrator.displayAllPatients());
		
		menu();
	}
	
	public static void sendPatientToLab() {
		System.out.println("\n----- Enviar paciente al laboratorio -----\n");
		
		System.out.println("�A qu� unidad desea enviar el paciente?"
				+ "\n1) Prop�sito general."
				+ "\n2) Hematolog�a.");
		int selection = Integer.parseInt(in.nextLine());
		
		switch (selection) {
		case 1:
			labAdministrator.sendPatient2Lab(false);
			break;
			
		case 2:
			labAdministrator.sendPatient2Lab(true);
			break;

		default:
			System.out.println("\nLa opci�n que elegiste no es v�lida. Intenta otra vez.");
			sendPatientToLab();
			break;
		}
		
		menu();	
	}
	
	public static void searchPatient() {
		System.out.println("\n----- Buscar paciente -----\n");
		
		System.out.println("Digite el n�mero de identificaci�n del paciente que busca:");
		String id = in.nextLine();
		
		if(labAdministrator.searchPatient(id) != null) {
			System.out.println("\nUsuario encontrado:"
					+ labAdministrator.searchPatient(id).toString());
		} else {
			System.out.println("\nEl usuario que busca no se encuntra en el sistema.");
		}
		
		menu();
	}
}
