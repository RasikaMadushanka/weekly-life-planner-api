package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.Meal_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Meal_Repository extends JpaRepository<Meal_entity,Long> {
}
