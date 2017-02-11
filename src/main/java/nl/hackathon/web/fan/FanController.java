package nl.hackathon.web.fan;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.adridadou.ethereum.EthereumFacade;
import org.adridadou.ethereum.keystore.AccountProvider;
import org.adridadou.ethereum.values.EthAccount;
import org.adridadou.ethereum.values.EthAddress;
import org.adridadou.ethereum.values.EthValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kivanov on 11/02/2017.
 */
@RestController
@RequestMapping(value = "/fan")
public class FanController {

    @Value("${main.pkey}")
    private String mainPKey;
    @Value("${contract.musician.wallet}")
    private String walletAddress;

    @Autowired
    private EthereumFacade ethereumFacade;

    @RequestMapping(value = "/donate", method = POST)
    @ResponseBody
    public void donate() throws ExecutionException, InterruptedException {
        ethereumFacade.sendEther(getSender(), EthAddress.of(walletAddress), EthValue.wei(BigInteger.TEN)).get();
    }

    private EthAccount getSender() {
        return AccountProvider
                .fromPrivateKey(mainPKey);
    }

}
