package com.pa.revmgmt.repo;

import com.pa.revmgmt.domain.Client;
import com.pa.revmgmt.domain.ClientType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    /**
     * Lookup client by Phone Number
     * @param phoneNumber  the client phone
     * @return  Client if found, null otherwise
     */
    Client findByPhoneNumber(final String phoneNumber);


    /**
     * Lookup client by client type
     * @param clientType    client type(Business or Individual)
     * @return the List of clients
     */
    List<Client> findByClientType(ClientType clientType);
}
