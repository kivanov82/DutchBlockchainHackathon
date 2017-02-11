package nl.hackathon;

import java.io.IOException;

import org.adridadou.ethereum.EthereumFacade;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

/**
 * Created by kivanov on 11/02/2017.
 */
@Service
public class RatingService {

    public String getRatingAbi() {
        String abi = null;
        try {
            abi = IOUtils.toString(this.getClass().getResourceAsStream("/contracts/RatingContract.interface"), EthereumFacade.CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return abi;
    }

}
