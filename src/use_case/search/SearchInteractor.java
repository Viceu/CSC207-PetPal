package use_case.search;

import app.SearchUseCaseFactory;
import data_access.ApiPetDataAccessObject;
import entities.CommonPetFactory;
import entities.Pet;
import entities.PetFactory;

import java.util.*;

public class SearchInteractor implements SearchInputBoundary {
    final SearchPetDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    final PetFactory petFactory;

    public SearchInteractor(SearchPetDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary,
                            PetFactory petFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
        this.petFactory = petFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        Map<String, String> requirements = searchInputData.getRequirements();

        // based on requirements, retrieve pets from API DAO and compile into map

        List<Integer> petIDs = userDataAccessObject.getIDs();
        Map<Integer, Pet> displayPetMap = new HashMap<>();

        for (Integer id : petIDs) {
            Pet pet = userDataAccessObject.getPet(id);
            if (!pet.getAdoptable()) {
                continue;
            }
            for (String req : requirements.keySet()) {

                if (req.equals("Species: ")) {
                    if (pet.getSpecies().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Age: ")) {
                    if (pet.getAge().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Sex: ")) {
                    if (pet.getGender().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Size: ")) {
                    if (pet.getSize().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Coat: ")) {
                    if (pet.getCoat().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Breed: ")) {

                    if (pet.getBreed().get("secondary") != null) {
                        if ((pet.getBreed().get("secondary").equals(requirements.get(req)))) {
                            displayPetMap.put(id, pet);
                            userDataAccessObject.save(pet);
                        }
                    } else if (pet.getBreed().get("mixed") != null) {
                        if ((pet.getBreed().get("mixed").equals(requirements.get(req)))) {
                            displayPetMap.put(id, pet);
                            userDataAccessObject.save(pet);
                        }
                    } else if (pet.getBreed().get("primary") != null) {
                        if ((pet.getBreed().get("primary").equals(requirements.get(req)))) {
                            displayPetMap.put(id, pet);
                            userDataAccessObject.save(pet);
                        }
                    } else if (pet.getBreed().get("unknown") != null) {
                        if ((pet.getBreed().get("unknown").equals(requirements.get(req)))) {
                            displayPetMap.put(id, pet);
                            userDataAccessObject.save(pet);
                        }
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Color: ")) {
                    String[] colors = requirements.get(req).split(",");
                    for (String color : colors) {
                        if ((pet.getColors().contains(color))) {
                            displayPetMap.put(id, pet);
                            userDataAccessObject.save(pet);
                        } else {
                            if (displayPetMap.containsValue(pet)) {
                                displayPetMap.remove(pet.getPetID());
                            }
                            break;
                        }
                    }

                }

                if (req.equals("House-trained: ")) {
                    if ((pet.getAttributes().get("house_trained")).toString().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Declawed: ")) {
                    if (pet.getAttributes().get("declawed") == null) {
                        break;
                    }
                    if ((pet.getAttributes().get("declawed")).equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Good with kids: ")) {
                    if (pet.getEnvironment().get("children") == null) {
                        break;
                    }
                    if ((pet.getEnvironment().get("children")).toString().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Good with (other) dogs: ")) {
                    if (pet.getEnvironment().get("dogs") == null) {
                        break;
                    }
                    if ((pet.getEnvironment().get("dogs")).toString().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Good with (other) cats: ")) {
                    if (pet.getEnvironment().get("cats") == null) {
                        break;
                    }
                    if ((pet.getEnvironment().get("cats")).toString().equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Phone: ")) {
                    if (pet.getContact().get("phone") == null) {
                        break;
                    }
                    if ((pet.getContact().get("phone")).equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }

                if (req.equals("Email: ")) {
                    if (pet.getContact().get("email") == null) {
                        break;
                    }
                    if ((pet.getContact().get("email")).equals(requirements.get(req))) {
                        displayPetMap.put(id, pet);
                        userDataAccessObject.save(pet);
                    } else {
                        if (displayPetMap.containsValue(pet)) {
                            displayPetMap.remove(pet.getPetID());
                        }
                        break;
                    }
                }
            }
        }
        if (displayPetMap.isEmpty()) {
            searchPresenter.prepareFailView("No search results.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(displayPetMap, true);
            searchPresenter.prepareSuccessView(searchOutputData);

        }

//        // getting pet objects
//        List<Pet> masterPetList = new ArrayList<>(userDataAccessObject.getPets());
//        Map<Integer, Pet> displayPetMap = new HashMap<>();
//        for (Pet pet : masterPetList) {
//            if (!pet.getAdoptable()) {
//                continue;
//            } else if (masterPetList.contains(pet)) {
//                continue;
//            }
//            for (Map.Entry<String, String> req : requirements.entrySet()) {
//                if ((req.equals("name")) && !(pet.getName().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("colors")) && !(pet.getColors().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("breed")) && !(pet.getBreed().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("species")) && !(pet.getSpecies().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("coat")) && !(pet.getCoat().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("age")) && !(pet.getAge().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("attributes")) && !(pet.getAttributes().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("environment")) && !(pet.getEnvironment().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("description")) && !(pet.getDescription().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("contact")) && !(pet.getContact().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("gender")) && !(pet.getGender().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                } else if ((req.equals("size")) && !(pet.getSize().equals(req.getValue()))) {
//                    masterPetList.remove(pet);
//                }
//            }
//        }
//        // putting pet and id in hashmap
//        for (Pet pet1 : masterPetList) {
//            displayPetMap.put(pet1.getPetID(), pet1);
//        }


    }
}