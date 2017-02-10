package nl.hackathon.web.musician;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import nl.hackathon.ethereum.contracts.RatingContract;
import org.adridadou.ethereum.EthereumFacade;
import org.adridadou.ethereum.keystore.AccountProvider;
import org.adridadou.ethereum.values.ContractAbi;
import org.adridadou.ethereum.values.EthAccount;
import org.adridadou.ethereum.values.EthAddress;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Value("${contract.musician.wallet1}")
    private String wallet1Address;
    @Value("${contract.musician.rating}")
    private String ratingAddress;
    @Value("${main.pkey}")
    private String mainPKey;

    @Autowired
    private EthereumFacade ethereumFacade;

    @RequestMapping(value = "/getWalletBalance", method = GET)
    @ResponseBody
    public BigDecimal getWalletBalance(@RequestParam String walletName) {
        if (walletName.equalsIgnoreCase("wallet1")) {
            return ethereumFacade.getBalance(EthAddress.of(wallet1Address)).inEth();
        }
        //stub
        return BigDecimal.TEN;
    }

    @RequestMapping(value = "/getRating", method = GET)
    @ResponseBody
    public BigInteger getRating() {
        RatingContract ratingContract = ethereumFacade
                .createContractProxy(new ContractAbi(getWalletAbi()), EthAddress.of(ratingAddress), getSender(), RatingContract.class);
        return ratingContract.ratings(EthAddress.of(musicianAddress));
    }

    private EthAccount getSender() {
        return AccountProvider
                .fromPrivateKey(mainPKey);
    }

    private String getWalletAbi() {
        String abi = null;
        try {
            abi = IOUtils.toString(this.getClass().getResourceAsStream("/contracts/WalletContract.interface"), EthereumFacade.CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return abi;
    }

}
