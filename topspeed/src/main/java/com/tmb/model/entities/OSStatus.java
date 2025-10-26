package com.tmb.model.entities;

public enum OSStatus {

	APPROVED(1, "Aprovada"), IN_PROGRESS(2, "Em andamento"), COMPLETED(3, "Concluída"), CANCELED(4, "Cancelada"),
	WAITING_MATERIALS(5, "À espera de aprovação"), WAITING_APPROVAL(6, "À espera de materiais"),
	IN_PLANNING(7, "Em planejamento");

	private int id;
	private String name;

	private OSStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static OSStatus valueOf(int id) {
		for (OSStatus status : OSStatus.values()) {
			if (status.getId() == id) {
				return status;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
