package nl.hackathon.ethereum;

import static org.adridadou.ethereum.provider.EthereumJConfigs.ropsten;

import java.util.concurrent.ExecutionException;

import org.adridadou.ethereum.EthereumFacade;
import org.adridadou.ethereum.provider.EthereumFacadeProvider;
import org.adridadou.ethereum.provider.PrivateEthereumFacadeProvider;
import org.adridadou.ethereum.provider.PrivateNetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class NetManager {

    @Bean
    @Profile({ "integration" })
    public EthereumFacade ethereumRopstenNetFacade() throws ExecutionException, InterruptedException {
        return EthereumFacadeProvider.forNetwork(ropsten().fastSync(true)).create();
    }

    @Bean
    @Profile({ "development" })
    public EthereumFacade ethereumPrivateNetFacade() throws ExecutionException, InterruptedException {
        PrivateEthereumFacadeProvider privateNetwork = new PrivateEthereumFacadeProvider();
        return privateNetwork.create(PrivateNetworkConfig.config());
    }

}
