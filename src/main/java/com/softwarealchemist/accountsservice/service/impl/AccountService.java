package com.softwarealchemist.accountsservice.service.impl;

import static com.softwarealchemist.accountsservice.constants.AccountsConstants.*;

import com.softwarealchemist.accountsservice.dto.AccountsDTO;
import com.softwarealchemist.accountsservice.dto.CustomerDTO;
import com.softwarealchemist.accountsservice.entity.Accounts;
import com.softwarealchemist.accountsservice.entity.Customer;
import com.softwarealchemist.accountsservice.exception.CustomerAlreadyExistsException;
import com.softwarealchemist.accountsservice.exception.ResourceNotFoundException;
import com.softwarealchemist.accountsservice.mapper.AccountsMapper;
import com.softwarealchemist.accountsservice.mapper.CustomerMapper;
import com.softwarealchemist.accountsservice.repository.AccountsRepository;
import com.softwarealchemist.accountsservice.repository.CustomerRepository;
import com.softwarealchemist.accountsservice.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private AccountsRepository accountsRepository;

    private final CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());

        Optional<Customer> retrievedCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

        if (retrievedCustomer.isPresent()) {
            String message = String.format("Customer with phone %s already exists!", customerDTO.getMobileNumber());
            throw new CustomerAlreadyExistsException(message);
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("system");

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Long customerId = customer.getCustomerId();
        Accounts account = accountsRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customerId.toString()));
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(account, new AccountsDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {

        boolean isUpdated = false;

        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();

        if (accountsDTO != null) {
            Long accountNumber = accountsDTO.getAccountNumber();
            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString()));

            AccountsMapper.mapToAccounts(accountsDTO, accounts);

            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));

            CustomerMapper.mapToCustomer(customerDTO, customer);

            customerRepository.save(customer);

            isUpdated = true;

        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Long customerId = customer.getCustomerId();
        accountsRepository.deleteByCustomerId(customerId);
        customerRepository.deleteById(customerId);
        return true;
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(SAVINGS);
        account.setBranchAddress(ADDRESS);
        account.setCreatedBy("admin");
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }

}
