package alpos.controller;

import alpos.model.AuthorModel;
import alpos.model.BookModel;
import alpos.model.CategoryModel;
import alpos.model.PublisherModel;
import alpos.service.AuthorService;
import alpos.service.BookService;
import alpos.service.CategoryService;
import alpos.service.PublisherService;
import alpos.uploader.ImageUpload;
import alpos.uploader.ImageUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;


@Controller
@EnableWebMvc
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    @Qualifier("authorService")
    private AuthorService authorService;

    @Autowired
    @Qualifier("imageUploader")
    ImageUploader imageUploader;

    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("publisherService")
    private PublisherService publisherService;

    @Autowired
    @Qualifier("bookService")
    BookService bookService;

    @GetMapping(value = { "/books/add" })
    public String add(Locale locale, Model model) {
        List<AuthorModel> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        List<CategoryModel> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<PublisherModel> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("book", new BookModel());
        return "books/add";
    }


    @PostMapping(value = "/books")
    public String create(@ModelAttribute("book") @Validated BookModel bookModel, BindingResult bindingResult,
                         Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
        ImageUpload imageUpload = imageUploader.uploadFile(bookModel.getFile());
        if (imageUpload != null) {
            bookModel.setUpload(imageUpload);
        }
        if (bindingResult.hasErrors()) {
            logger.info("Returning register.jsp page, validate failed");
            return "books/add";
        }

        BookModel book = bookService.addBook(bookModel);
        return "static_pages/home";
    }
	
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(value="/books")
	public String index (Model model) {
		List<BookModel> books = bookService.findAll();
		model.addAttribute("books", books);
		return "books/index";
	}

    @GetMapping(value = "/books/{id}")
    public String show(@PathVariable Integer id, Model model, HttpServletRequest request, Authentication authentication)
            throws Exception {
        model.addAttribute("book", bookService.findBook(id));
        System.out.println("Show book");
        return "books/show";
    }

}
