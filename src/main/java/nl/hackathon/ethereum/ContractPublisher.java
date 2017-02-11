package nl.hackathon.ethereum;

import java.util.concurrent.ExecutionException;

import nl.hackathon.ethereum.contracts.RatingContract;
import nl.hackathon.ethereum.contracts.WalletContract;
import org.adridadou.ethereum.EthereumFacade;
import org.adridadou.ethereum.values.CompiledContract;
import org.adridadou.ethereum.values.EthAccount;
import org.adridadou.ethereum.values.EthAddress;
import org.adridadou.ethereum.values.SoliditySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kivanov on 10/02/2017.
 */
public class ContractPublisher {

    private final Logger log = LoggerFactory.getLogger(ContractPublisher.class);

    public EthAddress publishRatingContract(EthereumFacade facade, EthAccount mainAccount) throws ExecutionException, InterruptedException {
        //publish rating contract
        SoliditySource contractSource = SoliditySource.from(this.getClass().getResourceAsStream("/contracts/RatingContract.sol"));
        CompiledContract compiledContract = facade.compile(contractSource).get().get("RatingContract");
        EthAddress address = facade.publishContract(compiledContract, mainAccount).get();

        log.info("RatingContract is published " + address);
        facade.createContractProxy(compiledContract, address, mainAccount, RatingContract.class);
        return address;
    }

    public EthAddress publishWalletContract(EthereumFacade facade, EthAccount mainAccount, EthAddress ratingAddress, EthAddress musicianAddress)
            throws ExecutionException, InterruptedException {
        //publish wallet contract
        SoliditySource contractSource = SoliditySource.from(this.getClass().getResourceAsStream("/contracts/WalletContract.sol"));
        CompiledContract compiledContract = facade.compile(contractSource).get().get("WalletContract");
        EthAddress address = facade.publishContract(compiledContract, mainAccount, ratingAddress, musicianAddress).get();

        log.info("WalletContract is published " + address);
        WalletContract contract = facade.createContractProxy(compiledContract, address, mainAccount, WalletContract.class);
        return address;
    }

}
