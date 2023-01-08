package com.bankservice.bankservice.controller;

import com.bankservice.bankservice.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bank/account")
public class AccountRestController {

    private final IAccountService iAccountService;

    public AccountRestController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @GetMapping("/card-exist")
    public ResponseEntity<?> cardExist(@RequestParam String cardNumber) {
        return new ResponseEntity<>(iAccountService.cardExist(cardNumber), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getByCardNumber(@RequestParam String cardNumber,
                                             @RequestParam String pin,
                                             @RequestParam String fingerPrint,
                                             @RequestParam String authType) {
        return new ResponseEntity<>(iAccountService.login(cardNumber, pin, fingerPrint, authType), HttpStatus.OK);
    }

    @GetMapping("/dispenser/cash")
    public ResponseEntity<?> cashDisposer(@RequestParam String cardNumber,
                                             @RequestParam String amount) {
        return new ResponseEntity<>(iAccountService.cashDispenser(cardNumber,Double.parseDouble(amount)), HttpStatus.OK);
    }

    @GetMapping("/dispenser/check")
    public ResponseEntity<?> checkDisposer(@RequestParam String cardNumber,
                                             @RequestParam Double amount) {
        return new ResponseEntity<>(iAccountService.checkDispenser(cardNumber, amount), HttpStatus.OK);
    }

    @GetMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String cardNumber,
                                             @RequestParam Double amount) {
        return new ResponseEntity<>(iAccountService.withdraw(cardNumber, amount), HttpStatus.OK);
    }

    @GetMapping("/withdraw/complete")
    public ResponseEntity<?> complete(@RequestParam String cardNumber,
                                      @RequestParam Long transactionId) {
        return new ResponseEntity<>(iAccountService.completeTransaction(cardNumber, transactionId), HttpStatus.OK);
    }


}
