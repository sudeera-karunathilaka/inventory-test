package au.com.sudeera.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.sudeera.inventory.model.StockRecord;

/**
 * The Interface StockRecordRepository.
 */
public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {

}
