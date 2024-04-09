package kredinbizdeoffer.kredinbizdeoffer.controller;

import kredinbizdeoffer.kredinbizdeoffer.entity.Offer;
import kredinbizdeoffer.kredinbizdeoffer.enums.CreditCardType;
import kredinbizdeoffer.kredinbizdeoffer.enums.LoanType;
import kredinbizdeoffer.kredinbizdeoffer.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;
    @GetMapping("/getOfferByParams")
    public Optional<Offer> findByParams(
            @RequestParam(required = false) Integer bankId,
            @RequestParam(required = false) Integer cardId,
            @RequestParam(required = false) CreditCardType creditCardType,
            @RequestParam(required = false) Integer loanId,
            @RequestParam(required = false) LoanType loanType
    ) {
        return offerService.findByParams(bankId, cardId, creditCardType, loanId, loanType);

    }
    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok().body(offers);
    }

    @GetMapping("/byBankId/{bankId}")
    public ResponseEntity<List<Offer>> getOffersByBankId(@PathVariable Integer bankId) {
        List<Offer> offers = offerService.getOffersByBankId(bankId);
        return ResponseEntity.ok().body(offers);
    }

    @GetMapping("/byCreditCardType/{creditCardType}")
    public ResponseEntity<List<Offer>> getOffersByCreditCardType(@PathVariable CreditCardType creditCardType) {
        List<Offer> offers = offerService.getOffersByCreditCardType(creditCardType);
        return ResponseEntity.ok().body(offers);
    }

    @GetMapping("/byLoanType/{loanType}")
    public ResponseEntity<List<Offer>> getOffersByLoanType(@PathVariable LoanType loanType) {
        List<Offer> offers = offerService.getOffersByLoanType(loanType);
        return ResponseEntity.ok().body(offers);
    }

    @PostMapping("/createOffer")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Integer id, @RequestBody Offer updatedOffer) {
        Offer updated = offerService.updateOffer(id, updatedOffer);
        return ResponseEntity.ok().body(updated);
    }
}
