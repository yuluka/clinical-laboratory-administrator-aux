package model;

import java.util.Random;

public class Patient {

	private String name;
	private boolean priority;
	private String priorityStr;
	private String id;
	private String age;
	private String celNumber;
	private String address;
	private boolean unit;
	private String unitStr;
	private int priorityValue;

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
	public Patient(String name, boolean priority, String id, String age, String celNumber, String address, boolean unit,
			int priorityValue) {
		this.name = name;
		this.priority = priority;
		this.id = id;
		this.age = age;
		this.celNumber = celNumber;
		this.address = address;
		this.unit = unit;
		this.priorityValue = priorityValue;
		
		setUnitStr();
		setPriorityStr();
	}
	
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean getPriority() {
		return this.priority;
	}

	/**
	 * 
	 * @param priority
	 */
	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	public String getCelNumber() {
		return this.celNumber;
	}

	/**
	 * 
	 * @param celNumber
	 */
	public void setCelNumber(String celNumber) {
		this.celNumber = celNumber;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getUnit() {
		return this.unit;
	}

	/**
	 * 
	 * @param unit
	 */
	public void setUnit(boolean unit) {
		this.unit = unit;
	}

	public int getPriorityValue() {
		return this.priorityValue;
	}

	/**
	 * 
	 * @param priorityValue
	 */
	public void setPriorityValue(int priorityValue) {
		this.priorityValue = priorityValue;
	}
	
	public String getPriorityStr() {
		return priorityStr;
	}

	public void setPriorityStr() {
		if(priority) {
			this.priorityStr = "S�";
		} else {
			this.priorityStr = "No";
		}
	}

	public String getUnitStr() {
		return unitStr;
	}

	public void setUnitStr() {
		if(unit) {
			this.unitStr = "Hematolog�a";
		} else {
			this.unitStr = "Prop�sito general";
		}		
	}
	
	public void startTimeInLab(Laboratory lab) {
		new Thread(() -> {
			int minimunTimeInLab = 60;
			int maximunTimeInLab = 120;
			
			Random random = new Random();
			int timeInLab = random.nextInt(maximunTimeInLab) + minimunTimeInLab;
			
			System.out.println(timeInLab);
			
			for(int i = 0; i < timeInLab; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			lab.removePatient(this);
			
		}).start();
	}

	@Override
	public String toString() {
		return "\n***"
				+ "\nNombre: " + name + ""
				+ "\nPrioridad: " + priorityStr
				+ "\nN�mero de identificaci�n: " + id 
				+ "\nEdad: " + age 
				+ "\nN�mero de tel�fono: " + celNumber 
				+ "\nDirecci�n: " + address 
				+ "\nUnidad de destino: " + unitStr 
				+ "\nValor de prioridad: " + priorityValue
				+ "\n***\n";
	}
	
}