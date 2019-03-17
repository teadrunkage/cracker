package ru.ncedu.schek.cracker.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.ncedu.schek.cracker.entities.Comment;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.CommentRepository;
import ru.ncedu.schek.cracker.repository.ModelRepository;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;
import ru.ncedu.schek.cracker.repository.PhoneRepository;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;
import ru.ncedu.schek.cracker.websocket.GreetClient;

@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	ModelService modelService;
	@Autowired
	ModelRepository models;
	@Autowired
	CommentRepository comments;
	@Autowired
	PhoneService phoneService;
	@Autowired
	PhoneRepository phones;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		modelService.saveAllModels();
		
		Model test_model = new Model("Xiaomi", 12000, 12000);
		models.save(test_model);
		Comment test_comment = new Comment(test_model, 5, "Отличный телефон, надо брать!");
		Comment test_comment2 = new Comment(test_model, 3, "норм");
		Comment test_comment3 = new Comment(test_model, 1,null);
		test_comment3.setUsername("Злой карлик");
		comments.save(test_comment);
		comments.save(test_comment2);
		comments.save(test_comment3);
		Phone test_phone = new Phone(test_model, 12000, "black");
		phones.save(test_phone);
		models.save(test_model);

		GreetClient client1 = new GreetClient();
		client1.startConnection("127.0.0.1", 5555);
		String msg1 = client1.sendMessage("hello");
		String msg2 = client1.sendMessage("world");
		String terminate = client1.sendMessage(".");


	//	test_model.getPhones().iterator().next().getPictures().iterator().next().getLink();
	}
}
