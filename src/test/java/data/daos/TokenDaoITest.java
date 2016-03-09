package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        User user = (User) daosService.getMap().get("u4");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        assertNull(tokenDao.findByUser(user));
    }
    
    @Test
    public void testRemoveExpiredTokens() {
    	assertEquals(4, tokenDao.count());
    	Token token = new Token((User) daosService.getMap().get("u3"));
    	token.setExpirationDate(System.currentTimeMillis() - Token.TOKEN_LIFETIME);
    	tokenDao.save(token);
    	assertEquals(5, tokenDao.count());
    	tokenDao.removeExpiredTokens(System.currentTimeMillis());
    	assertEquals(4, tokenDao.count());
    }

}
