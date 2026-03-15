package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.Activity_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Activity_Repository extends JpaRepository<Activity_entity,Long> {
}
