package com.BankCoreAPI.Account_Registration_Card_Issuance.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.RegisterCustomer(customerRequest);

        // Returns HTTP 201 Created along with the unified account and card details payload
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/account-details")
    public ResponseEntity<CustomerResponse> getAccountDetails(@RequestParam String peselNumber) {
        CustomerResponse response = customerService.getAccountDetails(peselNumber);

        // Returns HTTP 200 OK along with the details payload
        return ResponseEntity.ok(response);
    }

}
