package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataStructures.Hashtable;
import dataStructures.PriorityQueue;
import dataStructures.Queue;

public class LaboratoryAdministrator {

	private final String SEPARATOR = ",";
	
	private Hashtable<Integer,Patient> patients;
	private PriorityQueue<Integer,Patient> generalPriorityPatients;
	private Queue<Patient> generalNonPriorityPatients;
	private PriorityQueue<Integer, Patient> hematologyPriorityPatients;
	private Queue<Patient> hematologyNonPriorityPatients;
	
	private Laboratory lab;

	public LaboratoryAdministrator() {
		this.patients = new Hashtable<>();
		this.generalPriorityPatients = new PriorityQueue<>();
		this.generalNonPriorityPatients = new Queue<>();
		this.hematologyPriorityPatients = new PriorityQueue<>();
		this.hematologyNonPriorityPatients = new Queue<>();
		
		this.lab = new Laboratory();
	}

	/**
	 * 
	 * @param name
	 * @param priority
	 * @param id
	 * @param age
	 * @param celNumber
	 * @param address
	 * @param unit
	 */
	public boolean addPatient(String name, boolean priority, String id, String age, 
			String celNumber, String address, boolean unit, int priorityValue) {
		if(searchPatient(id) != null) {
			return false;
		}
		
		Patient newPatient = new Patient(name, priority, id, age, celNumber, address, 
				unit, priorityValue);
		
		patients.insert(patients.size()+1, newPatient);
		sendPatientToQueue(newPatient);
		
		return true;
	}
	
	/**
	 * 
	 * @param newPatient
	 */
	public boolean addPatient(Patient newPatient) {
		if(searchPatient(newPatient.getId()) != null) {
			return false;
		}
		
		patients.insert(patients.size()+1, newPatient);
		sendPatientToQueue(newPatient);
		
		return true;
	}
	
	/**
	 * 
	 * @param newPatient
	 */
	public void sendPatientToQueue(Patient newPatient) {
		if(newPatient.getPriority()) {
			if(!newPatient.getUnit()) {
				generalPriorityPatients.insert(newPatient.getPriorityValue(), newPatient);
			} else {
				hematologyPriorityPatients.insert(newPatient.getPriorityValue(),newPatient);
			}
		} else {
			if(!newPatient.getUnit()) {
				generalNonPriorityPatients.enqueue(newPatient);
			} else {
				hematologyNonPriorityPatients.enqueue(newPatient);
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public Patient searchPatient(String id) {
		for (int i = 0; i < patients.size(); i++) {
			if(patients.get(i).getValue().getId().equalsIgnoreCase(id)) {
				return patients.get(i).getValue();
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param priorityValue
	 */
	public boolean assignPriority(String id, int priorityValue) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param unit
	 */
	public void sendPatient2Lab(boolean unit) {
		if(!unit) {
			if(generalPriorityPatients.size() != 0) {
				//Enviar paciente prioritario
				lab.enterPatient(generalPriorityPatients.extractMax().getValue());
			} else {
				//Enviar paciente no prioritaio
				lab.enterPatient(generalNonPriorityPatients.dequeue().getValue());
			}
		} else {
			if(hematologyPriorityPatients.size() != 0) {
				lab.enterPatient(hematologyPriorityPatients.extractMax().getValue());
			} else {
				lab.enterPatient(hematologyNonPriorityPatients.dequeue().getValue());
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public boolean egressPatient(String id) {
		throw new UnsupportedOperationException();
	}

	public void undo() {
		throw new UnsupportedOperationException();
	}

	public Hashtable<Integer, Patient> getAllPatients() {
		return patients;
	}
	
	public String displayAllPatients() {
		String info = "";
		
		if(patients.size() == 0) {
			return "No hay pacientes en el sistema.";
		}
		
		for (int i = 0; i < patients.size(); i++) {
			info += patients.get(i).getValue().toString();
		}
		
		return info;
	}
	
	public ArrayList<Patient> getAllPatientsInLab() {
		return lab.getPatientsInLab();
	}
	
	public String displayAllPatientsInLab() {
		String info = "";
		
		if(lab.getPatientsInLab().size() == 0) {
			return "El laboratorio est� vac�o.";
		}
		
		for (int i = 0; i < lab.getPatientsInLab().size(); i++) {
			info += lab.getPatientsInLab().get(i).toString();
		}
		
		return info;
	}

	public PriorityQueue<Integer,Patient> getHematologyPriorityPatients() {
		return hematologyPriorityPatients;
	}
	
	public String displayHematologyPriorityPatients() {
		return printPriorityQueue(hematologyPriorityPatients);
	}

	public Queue<Patient> getHematologyNonPriorityPatients() {
		return hematologyNonPriorityPatients;
	}
	
	public String displayHematologyNonPriorityPatients() {
		return printQueue(hematologyNonPriorityPatients);
	}

	public PriorityQueue<Integer,Patient> getGeneralPriorityPatients() {
		return generalPriorityPatients;
	}
	
	public String displayGeneralPriorityPatients() {
		return printPriorityQueue(generalPriorityPatients);
	}

	public Queue<Patient> getGeneralNonPriorityPatients() {
		return generalNonPriorityPatients;
	}
	
	public String displayGeneralNonPriorityPatients() {
		return printQueue(generalNonPriorityPatients);
	}

	private String printPriorityQueue(PriorityQueue<Integer, Patient> pQueue) {
		String info = "";
		
		if(pQueue.size() == 0) {
			return "No hay personas esperando en esta fila.";
		}
		
		for (int i = 0; i < pQueue.size(); i++) {
			info += pQueue.get(i).getValue().toString();
		}
		
		return info;
	}
	
	private String printQueue(Queue<Patient> queue) {
		String info = "";
		
		int size = queue.size();
		
		if(size == 0) {
			return "No hay personas esperando en esta fila.";
		}
		
		for (int i = 0; i < size; i++) {
			Patient auxPatient = queue.front().getValue();
			info += queue.dequeue().getValue().toString();
			
			queue.enqueue(auxPatient);
		}
		
		return info;
	}
	
	public void loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/DataBase.txt"));
			
			String line = br.readLine();
			
			while(line != null) {
				String[] parts = line.split(SEPARATOR);
				
				boolean priority = Boolean.parseBoolean(parts[1]);
				boolean unit = Boolean.parseBoolean(parts[6]);
				int priorityValue = Integer.parseInt(parts[7]);
				
				Patient newPatient = new Patient(parts[0],priority,parts[2],parts[3],
						parts[4],parts[5],unit,priorityValue);
				
				addPatient(newPatient);
				
				line = br.readLine();
			}
			
			br.close();
		
			System.out.println("\n�Pacientes cargados exitosamente!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}