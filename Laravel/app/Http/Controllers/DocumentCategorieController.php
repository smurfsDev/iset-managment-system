<?php

namespace App\Http\Controllers;

use App\Models\DocumentCategorie;
use Illuminate\Http\Request;

class DocumentCategorieController extends Controller
{

    
    public function get (){
        $categories = DocumentCategorie::all();
       if($categories->isEmpty()){
           return response()->json(['message' => 'Aucune catégorie de document n\'est disponible'], 404);
        }
        return response()->json($categories, 200);
}
      public function showDocumentCategorie(){
        $DC = DocumentCategorie::orderBy("updated_at","desc")->paginate(5);
        if(sizeof($DC)>0){
        return response()->json(["data"=>$DC], 200);
        }
        else
            return response()->json([
                "aucune Categorie"
            ], 404);
    }

     function createDocumentCategorie(Request $request){
        $idResponsable = $request->user()->id;
        $title = $request->input('title');
        
        $newDC= array(
            "title" => $title,
            "idResponsable" => $idResponsable
        );

         $createNewDC = DocumentCategorie::create($newDC);

        if ($createNewDC) {
            return response()->json([
                'data' => [
                    'message' => 'Categorie Ajouté avec succès',
                    'id' => $createNewDC->id,
                    'attributes' => $createNewDC
                ]
            ], 201);
        }
        return "Categorie Ajouté avec succès";
}

 public function updateDocumentCategorie($id, Request $request){

        $dc = DocumentCategorie::find($id);
        if ($dc) {

            $dc->title = $request->input('title') ? $request->input('title') : $dc->title;
           
            $dc->save();
            return response()->json([
                'message' => 'Categorie mis à jour',
                'id' => $dc->id,
                'attributes' => $dc
            ], 201);
        } else {
            return response()->json([
                "Categorie n'existe pas"
            ], 404);
        }
    }
    
public function deleteDocumentCategorie($id){

        $dc = DocumentCategorie::find($id);

        if ($dc){
            $dc->delete();
            return response()->json([
                'type' => 'CategorieDocument',
                'message' => 'Categorie Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'CategorieDocument',
                'message' => "Categorie n'existe pas"
            ], 404);
        }
    }


    
}
