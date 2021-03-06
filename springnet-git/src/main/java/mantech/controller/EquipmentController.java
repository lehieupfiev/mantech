/**
 * Written by Long Nguyen <chautinhlong@gmail.com>
 * FREE FOR ALL BUT DOES NOT MEAN THERE IS NO PRICE.
 */
package mantech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.lilylnx.springnet.util.ClientUtils;

import mantech.controller.helpers.RName;
import mantech.controller.helpers.RStatus;
import mantech.controller.helpers.ResponseMessage;
import mantech.controller.helpers.TemplateKeys;
import mantech.domain.Category;
import mantech.domain.Equipment;
import mantech.repository.CategoryRepository;
import mantech.repository.EquipmentRepository;
import mantech.service.EquipmentService;

/**
 * @author Long Nguyen
 * @version $Id: Equipment.java,v 1.0 Sep 12, 2011 1:30:35 PM nguyenlong Exp $
 */
@Controller
@SessionAttributes("equipment")
public class EquipmentController {

  @Autowired
  private EquipmentRepository equipmentRepo;

  @Autowired
  private EquipmentService equipmentService;
  
  @Autowired
  private CategoryRepository categoryRepo;

  @Autowired
  private ClientUtils clientUtils;
  
  @RequestMapping(value = "/equipment", params = "action=list", method = RequestMethod.GET)
  public String list(@RequestParam(value="page", required=false, defaultValue="1") int page,
      ModelMap model) {

    int pageCount;
    if (equipmentRepo.count().intValue() % 5 == 0) {
      pageCount = equipmentRepo.count().intValue() / 5;
    }
    else {
      pageCount = (equipmentRepo.count().intValue() / 5) + 1;
    }

    if (page < 1 || page > pageCount) {
      model.addAttribute("msg", "Nothing to show");
    }
    else {
      List<Equipment> listEquipment = equipmentService.paginate(page);
      model.addAttribute("listEquipment", listEquipment);
    }
    
    model.addAttribute("pageCount", pageCount);
    return TemplateKeys.EQUIPMENT_LIST;

  }
  
  @RequestMapping(value = "/equipment", params = "action=add", method = RequestMethod.GET)
  public String insert(ModelMap model) {
    List<Category> category = categoryRepo.findAll();
    model.addAttribute("category", category);
    return "/equipment/add";
  }
  
  @RequestMapping(value = "/equipment/addSave", method = RequestMethod.POST)
  public ResponseEntity<String> insertSave(@RequestParam(value="name") String name,
      @RequestParam(value="catId") int id, ModelMap model) {

    ResponseMessage respMessage = new ResponseMessage(RName.ADD, RStatus.FAIL, null);
    try {
      Category category = categoryRepo.get(id);
      Equipment equipment = new Equipment();
      equipment.setName(name);
      equipment.setCategory(category);
      
      equipmentRepo.save(equipment);
      respMessage.setStatusAndMessage(RStatus.SUCC, String.format("Inserted equipment: <strong>%s </strong>", name));
    }
    catch(Exception e) {
      respMessage.setStatusAndMessage(RStatus.ERROR, e.getMessage());
    }
    return clientUtils.createJsonResponse(respMessage);
  }
  
  @RequestMapping(value = "/equipment", params = "action=edit", method = RequestMethod.GET)
  public String update(@RequestParam(value="id", required=false, defaultValue="0") int id,
      ModelMap model) {

    Equipment equipment = equipmentRepo.get(id);
    List<Category> listCategory = categoryRepo.findAll();
    model.addAttribute("equipment", equipment);
    model.addAttribute("listCategory", listCategory);
    return TemplateKeys.EQUIPMENT_EDIT;
  }
  
  @RequestMapping(value = "/equipment/editSave", method = RequestMethod.POST)
  public ResponseEntity<String> updateSave(@RequestParam(value="id") int id, @RequestParam(value="catId") int catId,
      @RequestParam(value="name") String name, ModelMap model) {

    ResponseMessage respMessage = new ResponseMessage(RName.UPDATE, RStatus.FAIL, null);
    
    try{
      Equipment equipment = equipmentRepo.get(id);
      Category newCate = categoryRepo.get(catId);
      equipment.setName(name);
      equipment.setCategory(newCate);
      equipmentRepo.update(equipment);
      respMessage.setStatusAndMessage(RStatus.SUCC, String.format("Updated equipment: <strong>%s </strong> successfully.",
          equipment.getName()));
    }
    catch(Exception e) {
      respMessage.setStatusAndMessage(RStatus.ERROR, e.getMessage());
    }
    
    return clientUtils.createJsonResponse(respMessage);
  }

}
