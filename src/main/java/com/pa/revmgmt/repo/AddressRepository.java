package com.pa.revmgmt.repo;

import com.pa.revmgmt.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
