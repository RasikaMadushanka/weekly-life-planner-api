package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.User_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Repository extends JpaRepository<User_entity,Long> {
}
