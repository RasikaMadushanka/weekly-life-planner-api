package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.Task_entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface Task_Repository extends JpaRepository<Task_entity,Long> {
    List<Task_entity> findByUserId(Long userId);}
