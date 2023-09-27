package com.capstone.progettofinale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.capstone.progettofinale.auth.SecurityConfig;
import com.capstone.progettofinale.payload.AppartamentoPayload;
import com.capstone.progettofinale.payload.CittàPayload;
import com.capstone.progettofinale.payload.DestinazionePayload;
import com.capstone.progettofinale.payload.HotelPayload;
import com.capstone.progettofinale.payload.LoginSuccessfullPayload;
import com.capstone.progettofinale.payload.UserLoginPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ProgettofinaleApplication.class)
@WebAppConfiguration
@Import(SecurityConfig.class)
@EnableWebMvc

class ProgettofinaleApplicationTests {

	static Long[] apprtIds = { 32l, 22l, 23l, 26l, 29l, 35l, 38l, 41l, 44l, 47l, 50l, 53l, 19l };
	static String[] apprtImgs = { "https://txc5xc6e.cdn.imgeng.in/custom/01505/foto/20220111115752-1.jpg",
			"https://dellaspiga.com/wp-content/uploads/2018/05/Parigi-Residence-5-1500x630.jpg",
			"https://www.berlino.com/wp-content/uploads/sites/13/Appartamenti.jpg",
			"https://www.studioimmedia.it/4668-thickbox_default/appartamento-vendita-firenze-in-villa-rinascimentale-.jpg",
			"https://www.nextbologna.it/wp-content/uploads/2022/03/next-bologna-appartamento-3.jpg",
			"https://media.gabetti.it/getImage.ashx?f=2378/be6b317a-6a39-4e64-8133-7fdf7eef11fc-y.jpg&r=c&x=380&y=284&c=FFFFFF&l=2",
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLXs19pIHW68HN15EwhBYaq1pdCJZ7XRdb1Q&usqp=CAU",
			"https://images-1.casa.it/360x265/listing/0/55/39/fd/489831195.jpg",
			"https://immobili.aaannunci.it/pics/25/89/11505874.jpg",
			"https://www.facileristrutturare.it/wp-content/uploads/2021/09/IMG_0072-HDR.jpg",
			"https://citynews-baritoday.stgy.ovh/~media/horizontal-mid/33420609908020/property-35e1a1acd2cbcda2877f3f0ef7b69f32-106419825-2.jpg",
			"https://media-cdn.tripadvisor.com/media/vr-splice-j/04/ba/a6/19.jpg",
			"https://static.citiesreference.com/pictures/rome/rome-apartment-2130Rome-p60.jpg" };

	static Long[] destIds = { 2l, 10l, 12l, 16l, 19l, 22l, 25l };
	static String[] destImgs = {
			"https://www.dolcevitaonline.it/wp-content/uploads/2023/01/francia-legalizzazione.jpeg",
			"https://www.ministeroturismo.gov.it/wp-content/uploads/2023/05/Pizza_LakeComo-scaled.jpg",
			"https://www.germania.info/wp-content/uploads/sites/69/germania.jpg",
			"https://staticgeopop.akamaized.net/wp-content/uploads/sites/32/2023/06/10-curiosita-sicilia.jpg",
			"https://content.imperatore.it/wp-content/uploads/2022/07/AdobeStock_69906199.jpeg",
			"https://www.costacrociere.it/content/dam/costa/costa-magazine/articles-magazine/travel/apulia-travel/puglia_m.jpg.image.694.390.low.jpg",
			"https://www.archetravel.com/wp-content/uploads/2020/10/toscana-cosa-vedere_inside.jpg" };

	static Long[] hotelIds = { 33l, 17l, 18l, 2l, 20l, 21l, 24l, 25l, 27l, 28l, 30l, 31l, 34l, 36l, 37l, 39l, 40l, 42l,
			43l, 45l, 46l, 48l, 49l, 51l, 52l, 54l, 55l };
	static String[] hotelImgs = {
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/470329651.jpg?k=491504641e09e7a23f8b3ea2a883f4bd943f7490f92d0dab58609356c1e4fcf9&o=&hp=1",
			"https://www.hotelromaflorence.com/sites/www.hotelromaflorence.com/files/styles/banner_promozione_900x500_/public/images/Hotel_Roma_Firenze_DoppiaVista_FI008.jpg?itok=LxcFfbBU",
			"https://hotelbologna.pisa.it/wp-content/uploads/2016/04/56.jpg",
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/321843701.jpg?k=d21cd11b0a02a5cfe655b161db77c1eadc67d86ba9c87997ca4c7eaff9799529&o=&hp=1",
			"https://staticfanpage.akamaized.net/wp-content/uploads/sites/33/2022/01/BH-Paris-Penthouse-Master-Bedroom-Night.jpg",
			"https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1d/47/4c/dd/hotel-paradiso.jpg?w=700&h=-1&s=1",
			"https://static.leonardo-hotels.com/image/rcdbl_lbbcs_01_dec19_4000x2600_474e5ebe411c7286f583d69af7fd424e_1200x780_mobile_3.jpeg",
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/188925257.jpg?k=88dea65295006ed88e13222b191378957aec1a5233936b049bf470bd80e5c69a&o=&hp=1",
			"https://hotelbologna.pisa.it/wp-content/uploads/2016/04/Camera2_4547-700x400.jpg",
			"https://hotelbologna.pisa.it/wp-content/uploads/2016/04/dopia.jpg",
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/318751787.jpg?k=a1f519c926088b1f8796232d8f84b58b836d962431325b5c44493602ec3abb8b&o=&hp=1",
			"https://foto.hrsstatic.com/fotos/0/2/1280/1280/100/FFFFFF/http%3A%2F%2Ffoto-origin.hrsstatic.com%2Ffoto%2F0%2F0%2F1%2F8%2F001885%2F001885_z33_20691452.jpg/OuIEwiheokutAsfTTXEK9g%3D%3D/2880%2C1620/6/BB_Hotel_Firenze_City_Center-Florence-Double_room_standard-2-1885.jpg",
			"https://dynamic-media-cdn.tripadvisor.com/media/photo-o/24/ff/e5/4d/comfort.jpg?w=700&h=-1&s=1",
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/299783402.jpg?k=e309c6cdaab9ae6ca8c4607faf96043358702a71c214b788d56cba3bc374ec14&o=&hp=1",
			"https://q-xx.bstatic.com/xdata/images/hotel/max500/15122737.jpg?k=94f3ada7be28ea9960c2d477427b77183c9615bdeab8cd78e32002f120fdc617&o=",
			"https://book.bestwestern.it/IT/immagini/98233/17cf8e86-c6ec-46a2-8a85-e236d7d6fdc4/fsp/best-western-ai-cavalieri-hotel---palermo.jpg",
			"https://media-cdn.tripadvisor.com/media/photo-s/19/24/9d/3f/b-b-hotel-palermo-quattro.jpg",
			"https://cdn0.matrimonio.com/vendor/2638/3_2/960/jpg/2-Camera.jpeg",
			"https://cdn.blastness.info/media/931/camere/thumbs/full/villathena_villasuite2_top.jpg",
			"https://birkinhotel.com/wp-content/uploads/2023/05/birkin-castello-120-cagliari-suite.jpg",
			"https://static.charmingsardinia.com/hotels/959/static/files/hotelvillafanny-sardinia1.jpg",
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/73300687.jpg?k=de8718085682481a770be1712fcba378f8bd92825fa2bcc3fff036b7d640e27f&o=&hp=1",
			"https://cf.bstatic.com/xdata/images/hotel/max1024x768/58227705.jpg?k=257b22aacf33610e53ffd7a36fc85df84b2925c8d38644d02c89224d03777736&o=&hp=1",
			"https://www.ahstatic.com/photos/b8z2_ho_01_p_1024x768.jpg",
			"https://media.hrs.com/media/image/fd/0c/d7/Aloisi_Hotel-Lecce-Doppelzimmer_Komfort-8-482914.jpg",
			"https://x.cdrst.com/foto/hotel-sf/2f41/granderesp/hi-hotel-bari-habitacion-f447c73.jpg",
			"https://www.cibotoday.it/~media/original-hi/18323124329772/una-camera-d-hotel-con-vista.jpeg" };

	static Long[] cityIds = { 15l, 9l, 14l, 17l, 18l, 20l, 21l, 23l, 24l, 26l, 27l, 28l, 29l };
	static String[] cityImgs = {
			"https://www.italia.it/content/dam/tdh/en/interests/lombardia/milano/milano-in-48-ore/media/20220119115535-piazza-del-duomo-all-alba-milano-lombardia-shutterstock-1161075943-rid.jpg",
			"https://www.nationalgeographic.it/upload/ngi-hero/colosseum-daylight-rome-italy.jpg",
			"https://www.italia.it/content/dam/tdh/it/interests/emilia-romagna/bologna/bologna/media/bologna-torri-emilia-romagna-gettyimages.jpg",
			"https://tourscanner.com/blog/wp-content/uploads/2021/12/things-to-do-in-Palermo.jpg",
			"https://mediaim.expedia.com/destination/1/4828942084b2cbf4ed12767ce46c8e6e.jpg",
			"https://birkinhotel.com/wp-content/uploads/2021/03/birkinhotel-cagliari-11-scaled.jpg",
			"https://www.hotelgabbianoazzurro.com/Cms_Data/Contents/hga_it/Folders/Blog/~contents/R7F2KZMK6VR65N25/olbia-bella-.jpg",
			"https://www.consigliamidove.it/wp-content/uploads/2019/05/Lecce.jpg",
			"https://turismo.puglia.it/wp-content/uploads/sites/124/bari-veduta-aerea-hd.jpg",
			"https://tourismmedia.italia.it/is/image/mitur/20210401173629-firenze-toscana-gettyimages-1145040590?wid=1600&hei=900&fit=constrain,1&fmt=webp",
			"https://static2-viaggi.corriereobjects.it/wp-content/uploads/2015/06/pisa-getty-1080x794.jpg?v=1572449393",
			"https://www.iltuopostonelmondo.com/wp-content/uploads/2014/11/Torre-Eiffel-illuminata-e1490007847959.jpg",
			"https://www.startmag.it/wp-content/uploads/image-8.jpg" };

	static List<CittàPayload> cityPayloads = new ArrayList<>();
	static List<HotelPayload> hotelPayloads = new ArrayList<>();
	static List<DestinazionePayload> destPayloads = new ArrayList<DestinazionePayload>();
	static List<AppartamentoPayload> apprtPayloads = new ArrayList<AppartamentoPayload>();

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;
	private MockMvc mvc;

	@BeforeAll
	public static void init() {
		assertEquals(cityIds.length, cityImgs.length);
		for (int i = 0; i < cityIds.length; i++) {
			CittàPayload cp = new CittàPayload();
			cp.setId(cityIds[i]);
			if (i < cityImgs.length) {
				cp.setImgUrl(cityImgs[i]);
			}
			cityPayloads.add(cp);
		}

		assertEquals(hotelIds.length, hotelImgs.length);
		for (int i = 0; i < hotelIds.length; i++) {
			HotelPayload hp = new HotelPayload();
			hp.setId(hotelIds[i]);
			if (i < hotelImgs.length) {
				hp.setUrlImmagine(hotelImgs[i]);
			}
			hotelPayloads.add(hp);

		}

		assertEquals(destIds.length, destImgs.length);
		for (int i = 0; i < destIds.length; i++) {
			DestinazionePayload dp = new DestinazionePayload();
			dp.setId(destIds[i]);
			if (i < destImgs.length) {
				dp.setImgUrl(destImgs[i]);
			}
			destPayloads.add(dp);
		}

		assertEquals(apprtIds.length, apprtImgs.length);
		for (int i = 0; i < apprtIds.length; i++) {
			AppartamentoPayload ap = new AppartamentoPayload();
			ap.setId(apprtIds[i]);
			if (i < apprtImgs.length) {
				ap.setUrlImmagine(apprtImgs[i]);
			}
			apprtPayloads.add(ap);
		}
	}

	@BeforeEach
	public void setup() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter
				.setObjectMapper(new ObjectMapper().registerModule(new ParameterNamesModule()));
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())

				.build();
	}
	@Test
	void contextLoads() throws Exception {


		UserLoginPayload p = new UserLoginPayload("super.admin@test.com", "1234");
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(p))).andDo(new ResultHandler() {

					@Override
					public void handle(MvcResult result) throws Exception {
						String payload = result.getResponse().getContentAsString();
						LoginSuccessfullPayload lsp = mapper.readValue(payload,
								LoginSuccessfullPayload.class);
						log.info("auth token: " + lsp.getAccessToken());

						mvc.perform(
								MockMvcRequestBuilders.get("/mete/città").header("Authorization",
										"Bearer " + lsp.getAccessToken()))
								.andExpect(status().isOk());

						mvc.perform(MockMvcRequestBuilders.get("/mete/città/9").header("Authorization",
								"Bearer " + lsp.getAccessToken())).andExpect(status().isOk())
								.andExpect(jsonPath("$.nome").value("Roma"));

						cityPayloads.forEach(cp -> {
							try {
								mvc.perform(MockMvcRequestBuilders.put("/mete/città/" + cp.getId())
										.header("Authorization", "Bearer " + lsp.getAccessToken())
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.content(mapper.writeValueAsString(cp))

						).andExpect(status().isOk());
							} catch (Exception e) {
								e.printStackTrace();
							}
						});

						hotelPayloads.forEach(hp -> {
							try {
								mvc.perform(MockMvcRequestBuilders.put("/alloggi/hotels/" + hp.getId())
										.header("Authorization", "Bearer " + lsp.getAccessToken())
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.content(mapper.writeValueAsString(hp))

						).andExpect(status().isOk());
							} catch (Exception e) {
								e.printStackTrace();
							}
						});

						apprtPayloads.forEach(ap -> {
							try {
								mvc.perform(MockMvcRequestBuilders.put("/alloggi/appartamenti/" + ap.getId())
										.header("Authorization", "Bearer " + lsp.getAccessToken())
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.content(mapper.writeValueAsString(ap))

						).andExpect(status().isOk());
							} catch (Exception e) {
								e.printStackTrace();
							}
						});

						destPayloads.forEach(dp -> {
							try {
								mvc.perform(MockMvcRequestBuilders.put("/mete/destinazioni/" + dp.getId())
										.header("Authorization", "Bearer " + lsp.getAccessToken())
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.content(mapper.writeValueAsString(dp))

						).andExpect(status().isOk());
							} catch (Exception e) {
								e.printStackTrace();
							}
						});

					}
				});
	}

}
