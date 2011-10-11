/**
 * Written by Long Nguyen <chautinhlong@gmail.com>
 * FREE FOR ALL BUT DOES NOT MEAN THERE IS NO PRICE.
 */
package mantech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import mantech.controller.helpers.TemplateKeys;
import mantech.domain.Category;
import mantech.repository.CategoryRepository;

/**
 * @author Long Nguyen
 * @version $Id: CategoryController.java,v 1.0 2011/09/08 23:07:48 nguyenlong Exp $
 */
@Controller
@SessionAttributes("category")
public class CategoryController {

  @Autowired
  private CategoryRepository categoryRepo;

  @RequestMapping(value = "/category", params = "action=list", method = RequestMethod.GET)
  public String list(ModelMap model){
    model.addAttribute("listCategory", categoryRepo.findAll());
    return TemplateKeys.CATEGORY_LIST;
  }
  
  @RequestMapping(value = "/category", params = "action=edit", method = RequestMethod.GET)
  public String update(@RequestParam(value="id", required=false, defaultValue="0") int id,
      ModelMap model) {

    if (id > 0) {
      Category category = categoryRepo.get(id);
      model.addAttribute("category", category);
    }
    else { 
      model.addAttribute("msg", "Khong co category de xu ly");
    }
    return TemplateKeys.CATEGORY_EDIT;
  }
  
  @RequestMapping(value = "/category/editSave", method = RequestMethod.GET)
  public String updateSave(ModelMap model) {
    return "redirect:" + list(model);
  }

  @RequestMapping(value = "/category/editSave", method = RequestMethod.POST)
  public String updateSave(@ModelAttribute("category") Category category, ModelMap model) {
    // TODO Validate tai day

    if (category != null) {
      categoryRepo.update(category);
      model.addAttribute("msg", "Successfully!");
      model.addAttribute("ok", true);
    }
    return update(category.getId(), model);
  }

}
