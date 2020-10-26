package alpos.controller;

import alpos.model.AuthorModel;
import alpos.model.BookModel;
import alpos.model.CategoryModel;
import alpos.model.PublisherModel;
import alpos.service.AuthorService;
import alpos.service.BookService;
import alpos.service.CategoryService;
import alpos.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        if (bindingResult.hasErrors()) {
            logger.info("Returning register.jsp page, validate failed");
            return "books/add";
        }

        BookModel book = bookService.addBook(bookModel);
        return "static_pages/home";
    }

}
