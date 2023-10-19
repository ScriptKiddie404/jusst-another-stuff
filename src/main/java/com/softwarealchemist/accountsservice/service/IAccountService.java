package com.softwarealchemist.accountsservice.service;

import com.softwarealchemist.accountsservice.dto.CustomerDTO;

public interface IAccountService {

    /**
     * This method creates an account.
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Account details based on a given mobile number.
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     * @param customerDTO - CustomerDTO object
     * @return boolean indicating if the update of Account details is successful or not.
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * @param mobileNumber - The mobile number of the account
     * @return boolean indicating if the deleting was successful or not
     */
    boolean deleteAccount(String mobileNumber);

}
