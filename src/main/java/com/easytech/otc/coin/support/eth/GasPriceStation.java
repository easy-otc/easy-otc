package com.easytech.otc.coin.support.eth;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.web3j.utils.Convert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GasPriceStation {

    private static final String URL            = "https://ethgasstation.info/index.php";
    private static final String SAFE_LOW_XPATH = "/html/body/div/div/div[3]/div[3]/div[3]/div/div[2]/table/tbody/tr[1]/td[2]";
    private static final String STANDARD_XPATH = "/html/body/div/div/div[3]/div[3]/div[3]/div/div[2]/table/tbody/tr[2]/td[2]";
    private static final String FAST_XPATH     = "/html/body/div/div/div[3]/div[3]/div[3]/div/div[2]/table/tbody/tr[3]/td[2]";

    @Data
    @AllArgsConstructor
    public static class RecomendedGasPrice {
        private BigInteger safeLow;
        private BigInteger standard;
        private BigInteger fast;
    }

    public static RecomendedGasPrice fetchRecomendedGasPrice() {

        try {
            Connection connect = Jsoup.connect(URL);
            String html = connect.get().body().html();

            TagNode tn = new HtmlCleaner().clean(html);
            Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String safeLowStr = ((NodeList) xPath.evaluate(SAFE_LOW_XPATH, dom, XPathConstants.NODESET)).item(0).getTextContent();
            String standardStr = ((NodeList) xPath.evaluate(STANDARD_XPATH, dom, XPathConstants.NODESET)).item(0).getTextContent();
            String fastStr = ((NodeList) xPath.evaluate(FAST_XPATH, dom, XPathConstants.NODESET)).item(0).getTextContent();

            BigInteger safeLow = Convert.toWei(new BigDecimal(safeLowStr), Convert.Unit.GWEI).toBigInteger();
            BigInteger standard = Convert.toWei(new BigDecimal(standardStr), Convert.Unit.GWEI).toBigInteger();
            BigInteger fast = Convert.toWei(new BigDecimal(fastStr), Convert.Unit.GWEI).toBigInteger();

            return new RecomendedGasPrice(safeLow, standard, fast);
        } catch (Exception e) {
            LOGGER.error("fetch recomended gas price fail");

            return null;
        }
    }
}