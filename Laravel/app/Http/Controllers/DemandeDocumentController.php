<?php

namespace App\Http\Controllers;

use App\Models\DemandeDocument;
use Illuminate\Http\Request;

class DemandeDocumentController extends Controller
{
  
    public function getDemandeDocument(Request $request){
            if ($request->user()->roles()->get()->contains('name', "admin")) {
            $DD = DemandeDocument::orderBy('created_at', 'desc')
                ->paginate(5);
            if (empty($DD)) {
                return response()->json(['message' => 'No Demande Document found'], 404);
            }
            return response()->json($DD, 200);
        }else{
            $DD = $request->user()->DemandeDocument ()->orderBy('updated_at', 'desc')->paginate(5);
            if (empty($DD)) {
                return response()->json(['message' => 'No Demande Document found'], 404);
            }
            return response()->json($DD, 200);
        }
    
    }
    
    public function createDemandeDocument(){
          $DD = new DemandeDocument();
          $DD->Titre = "Titre";
          $DD->Description = "Description";
          $DD->document_categories_id = "document_categories_id";
          $DD->idAdmin = "idAdmin";
          $DD->save();
          
        return response()->json($DD, 201);
                  
    }
  
    public function showDemandeDocument(){
        $DDs = DemandeDocument::with('categorie')->orderBy('updated_at', 'desc')->paginate(5);
                if (sizeof($DDs) > 0)
                    return response()->json(
                        $DDs,
                        200
                    );
                else
                    return response()->json([], 404);

    }
    
    public function showAll()
    {
        $DD = DemandeDocument::all();
        if (sizeof($DD) > 0) {
            return response()->json(["data" => $DD], 200);
        } else
            return response()->json([
                "aucun Demande Documents"
            ],
             404);
    }

    public function addDemandeDocument(Request $request)
    {
        $DD = new DemandeDocument();
        $DD->titre = $request->input('titre');
        $DD->description = $request->input('description');
        $DD->document_categories_id = $request->input('document_categories_id');
        $DD->idResponsable = $request->user()->id;
        $DD->save();
        return response()->json([
            'type' => 'Demande Document',
            'message' => 'Demande Document added',
            'attached' => true,
            "data" => DemandeDocument::find($DD->id)
        ], 201);
    }

    public function deleteDemandeDocument($id)
    {
        $DD = DemandeDocument::find($id);
        $DD->delete();
        return response()->json([
            'type' => 'Demande Document',
            'message' => 'Demande Document deleted',
            'attached' => true,
            "data" => DemandeDocument::find($id)
        ], 201);
    }

    public function updateDemandeDocument(Request $request,$id){
        $DDs = DemandeDocument::find($id);
        if ($DDs) {

            $DDs->titre = $request->input('titre') ? $request->input('titre') : $DDs->titre;
            $DDs->description = $request->input('description') ? $request->input('description') : $DDs->description;
            $DDs->document_categories_id = $request->input('document_categories_id') ? $request->input('document_categories_id') : $DDs->document_categories_id;
            $DDs->save();
            return response()->json([
                'message' => 'Demande Document mis à jour',
                'id' => $DDs->id,
                'attributes' => $DDs
            ], 201);
        } else {
            return response()->json([
                "Demande Document n'existe pas"
            ], 404);
        }
    
    }
    
   public function setReponse($id,Request $request){
        $repr = DemandeDocument::find($id);
        if ($repr) {
            $repr->reponse = $request->input('reponse')?$request->input('reponse'):$repr->reponse;
            $repr->file = $request->input('file')?$request->input('file'):$repr->file;
            $repr->idAdmin = $request->user()->id;
            $repr->save();
            return response()->json('reponse set',200);
        } else {
            return response()->json([
                'type'    => 'Demande Document',
                'message' => 'Demande Document non trouvée'
            ], 404);
        }
    }

}
