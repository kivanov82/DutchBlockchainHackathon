package nl.hackathon.ethereum;

import static org.adridadou.ethereum.ethj.provider.EthereumJConfigs.ropsten;

import java.util.concurrent.ExecutionException;

import org.adridadou.ethereum.EthereumFacade;
import org.adridadou.ethereum.ethj.provider.EthereumFacadeProvider;
import org.adridadou.ethereum.ethj.provider.PrivateEthereumFacadeProvider;
import org.adridadou.ethereum.ethj.provider.PrivateNetworkConfig;
import org.adridadou.ethereum.keystore.AccountProvider;
import org.adridadou.ethereum.values.CompiledContract;
import org.adridadou.ethereum.values.EthAccount;
import org.adridadou.ethereum.values.EthAddress;
import org.adridadou.ethereum.values.SoliditySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class NetManager {

    private final Logger log = LoggerFactory.getLogger(NetManager.class);

    @Bean
    @Profile({ "integration" })
    public EthereumFacade ethereumRopstenNetFacade() throws ExecutionException, InterruptedException {
        return EthereumFacadeProvider.forNetwork(ropsten().fastSync(true)).create();
    }

    @Bean
    @Profile({ "development" })
    public EthereumFacade ethereumPrivateNetFacade() throws ExecutionException, InterruptedException {
        PrivateEthereumFacadeProvider privateNetwork = new PrivateEthereumFacadeProvider();
        EthereumFacade facade = privateNetwork.create(PrivateNetworkConfig.config());

        EthAccount mainAccount = AccountProvider.fromSeed("cow");

        //publish rating contract
        SoliditySource contractSource = SoliditySource.from(this.getClass().getResourceAsStream("/contracts/RatingContract.sol"));
        CompiledContract compiledContract = facade.compile(contractSource).get().get("RatingContract");
        EthAddress address = facade.publishContract(compiledContract, mainAccount).get();

        log.info("Contract is published " + address);

        RatingContract proxy = facade.createContractProxy(compiledContract, address, mainAccount, RatingContract.class);

        Boolean result = proxy.vote(mainAccount.getAddress()).get();

        return facade;
    }

}
