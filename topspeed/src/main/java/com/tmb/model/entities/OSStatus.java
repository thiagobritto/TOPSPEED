package com.tmb.model.entities;

public enum OSStatus {

	APPROVED("Aprovada"), 
	IN_PROGRESS("Em andamento"), 
	COMPLETED("Concluída"), 
	CANCELED("Cancelada"),
	WAITING_MATERIALS("À espera de aprovação"), 
	WAITING_APPROVAL("À espera de materiais"),
	IN_PLANNING("Em planejamento");

	private String name;

	private OSStatus(String name) {
		this.name = name;
	}

	public static OSStatus fromName(String name) {
		for (OSStatus status : values()) {
			if (status.getName().equals(name)) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Não existe um OSStatus com esse Nome: " + name);
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
