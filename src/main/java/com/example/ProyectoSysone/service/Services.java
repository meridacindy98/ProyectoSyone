package com.example.ProyectoSysone.service;

import java.util.List;
import java.util.Optional;

import com.example.ProyectoSysone.entity.TipoAuto;

public interface Services <Entity, Id> {
	void save(Entity object);
	void delete(Entity object);
	void update(Entity object);
	Optional<TipoAuto> findById(Id id);
	List<Entity> findAll();
}
