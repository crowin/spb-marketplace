package ru.oreshnikova.marketplace.service;

import ru.oreshnikova.marketplace.entity.Supplier;
import ru.oreshnikova.marketplace.repository.SuppliersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j @AllArgsConstructor
public class SupplierService {
    private SuppliersRepository suppliersRepository;

    public List<Supplier> getAll() {
        return suppliersRepository.findAll();
    }

    public void addNewSupplier(Supplier supplier) {
        log.info("Add new {} supplier", supplier);
        suppliersRepository.save(supplier);
    }

    public void removeById(List<Long> ids) {
        log.info("Remove {} suppliers", ids);
        suppliersRepository.deleteAllById(ids);
    }

    public Supplier getById(Long id) {
        return suppliersRepository.findById(id).get();
    }
}
