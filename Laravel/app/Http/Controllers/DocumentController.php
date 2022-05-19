<?php

namespace App\Http\Controllers;

use App\Models\Document;
use Illuminate\Http\Request;

class DocumentController extends Controller
{
   public function getDocument(Request $request){
        
        $documents= Document::orderBy('updated_at')->paginate(5);
        if (sizeof($documents) > 0)
            return response()->json(
                $documents,
                200
            );
        else
            return response()->json([
                null
            ], 404);

    }

public function show($id){
    $documents = Document::orderBy('updated_at', 'desc')->paginate(5);
            if (sizeof($documents) > 0)
                return response()->json(
                    $documents,
                    200
                );
            else
                return response()->json([], 404);

    /* $documents = Document::where('document', $id)->get();
        if(empty($documents)){
            return response()->json(['message' => 'Aucun demande existe'], 404);
        }
        return response()->json($documents, 200);*/
    }


    function createDocument(Request $request){
        $idResponsable = $request->user()->id;
        $nom = $request->input('nom');
        $class = $request->input('class');
        $categorie = $request->input('categorie');
        $file = $request->input('file');

        $newDocument = array(
            "nom" => $nom,
            "class" => $class,
            "file" => $file,
            "categorie" => $categorie,
            "idResponsable" => $idResponsable
        );

         $createNewDocument = Document::create($newDocument);

        if ($createNewDocument) {
            return response()->json([
                'data' => [
                    'message' => 'Document Ajouté avec succès',
                    'id' => $createNewDocument->id,
                    'attributes' => $createNewDocument
                ]
            ], 201);
        }
        return "Document Ajouté avec succès";

}

 public function deleteDocument($id){

        $documents = Document::find($id);

        if ($documents){
            $documents->delete();
            return response()->json([
                'type' => 'Document',
                'message' => 'Document Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Document',
                'message' => 'Document n existe pas'
            ], 404);
        }
    }

 public function updateDocument($id, Request $request){

        $documents = Document::find($id);
        if ($documents) {

            $documents->nom = $request->input('nom') ? $request->input('nom') : $documents->nom;
            $documents->class = $request->input('class') ? $request->input('class') : $documents->class;
            $documents->file = $request->input('file') ? $request->input('file') : $documents->file;
            $documents->categorie = $request->input('categorie') ? $request->input('categorie') : $documents->categorie;
            
            $documents->save();
            return response()->json([
                'message' => 'Document mis à jour',
                'id' => $documents->id,
                'attributes' => $documents
            ], 201);
        } else {
            return response()->json([
                "Document n existe pas"
            ], 404);
        }
    }

}
