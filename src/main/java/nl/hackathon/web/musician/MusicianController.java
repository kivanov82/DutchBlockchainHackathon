package nl.hackathon.web.musician;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import nl.hackathon.RatingService;
import nl.hackathon.WalletService;
import nl.hackathon.ethereum.contracts.RatingContract;
import org.adridadou.ethereum.EthereumFacade;
import org.adridadou.ethereum.keystore.AccountProvider;
import org.adridadou.ethereum.values.ContractAbi;
import org.adridadou.ethereum.values.EthAccount;
import org.adridadou.ethereum.values.EthAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kivanov on 10/02/2017.
 */
@RestController
@RequestMapping(value = "/musician")
public class MusicianController {

    @Value("${musician.address}")
    private String musicianAddress;
    @Value("${contract.musician.wallet}")
    private String walletAddress;
    @Value("${contract.musician.rating}")
    private String ratingAddress;
    @Value("${main.pkey}")
    private String mainPKey;
    @Value("${main.address}")
    private String mainAddress;
    @Autowired
    private WalletService walletService;
    @Autowired
    private RatingService ratingService;


    @Autowired
    private EthereumFacade ethereumFacade;

    @RequestMapping(value = "/getWalletBalance", method = GET)
    @ResponseBody
    public BigInteger getWalletBalance() {
        return ethereumFacade.getBalance(EthAddress.of(walletAddress)).inWei();
    }

    @RequestMapping(value = "/getRating", method = GET)
    @ResponseBody
    public BigInteger getRating() {
        RatingContract ratingContract = ethereumFacade
                .createContractProxy(new ContractAbi(ratingService.getRatingAbi()), EthAddress.of(ratingAddress), getSender(), RatingContract.class);
        return ratingContract.ratings(EthAddress.of(musicianAddress));
    }

    @RequestMapping(value = "/rate", method = GET)
    @ResponseBody
    public Boolean rate() throws ExecutionException, InterruptedException {
        RatingContract ratingContract = ethereumFacade
                .createContractProxy(new ContractAbi(ratingService.getRatingAbi()), EthAddress.of(ratingAddress), getSender(), RatingContract.class);
        return ratingContract.rate(EthAddress.of(mainAddress), EthAddress.of(musicianAddress)).get();
    }

    private EthAccount getSender() {
        return AccountProvider
                .fromPrivateKey(mainPKey);
    }

}
