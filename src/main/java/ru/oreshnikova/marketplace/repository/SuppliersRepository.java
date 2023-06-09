package ru.oreshnikova.marketplace.repository;

import ru.oreshnikova.marketplace.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Long> {

}
