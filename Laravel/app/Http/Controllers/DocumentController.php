<?php

namespace App\Http\Controllers;

use App\Models\Document;
use Illuminate\Http\Request;

class DocumentController extends Controller
{

    public function getDocument()
    {

        $documents = Document::orderBy('updated_at')->paginate(5);
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

    public function show()
    {
        $documents = Document::orderBy('updated_at', 'desc')->paginate(5);
        if (sizeof($documents) > 0)
            return response()->json(
                $documents,
                200
            );
        else
            return response()->json([], 404);
    }

    function createDocument(Request $request)
    {
        $idResponsable = $request->user()->id;
        $nom = $request->input('nom');
        $class = $request->input('classe');
        $categorie = $request->input('categorie');
        $file = $request->input('file');

        $newDocument = array(
            "nom" => $nom,
            "classes_id" => $class,
            "file" => $file,
            "document_categories_id" => $categorie,
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

    public function deleteDocument($id)
    {

        $documents = Document::find($id);

        if ($documents) {
            $documents->delete();
            return response()->json([
                'type' => 'Document',
                'message' => 'Document Supprimé! '
            ], 204);
        } else {
            return response()->json([
                'type' => 'Document',
                'message' => "Document n'existe pas"
            ], 404);
        }
    }

    public function updateDocument($id, Request $request)
    {

        $documents = Document::find($id);
        if ($documents) {

            $documents->nom = $request->input('nom') ? $request->input('nom') : $documents->nom;
            $documents->classes_id = $request->input('classes_id') ? $request->input('classe') : $documents->class;
            $documents->file = $request->input('file') ? $request->input('file') : $documents->file;
            $documents->document_categories_id = $request->input('categorie') ? $request->input('document_categories_id') : $documents->categorie;

            $documents->save();
            return response()->json([
                'message' => 'Document mis à jour',
                'id' => $documents->id,
                'attributes' => $documents
            ], 201);
        } else {
            return response()->json([
                "Document n'existe pas"
            ], 404);
        }
    }


    public function search(Request $request)
    {

        $search = $request->search ?? "";
        $categorie = $request->categorie ?? "";
        $class = $request->class ?? "";
        $documents = [];
        if ($search == "" && $categorie == "all" && $class == "all") {
            $documents = Document::with('class')->with('categorie')
            ->orderBy('created_at', 'desc')
            ->paginate(5);
        }   else if($search == "" && $categorie == "all" && $class != "all"){
            $documents = Document::
                where('classes_id', $class)
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }   else if($search == "" && $categorie != "all" && $class == "all"){
            $documents = Document::
                where('document_categories_id', $categorie)
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }   else if($search == "" && $categorie != "all" && $class != "all"){
            $documents = Document::
                where('document_categories_id', $categorie)
                ->where('classes_id', $class)
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }   else if($search != "" && $categorie == "all" && $class == "all"){
            $documents = Document::
                where('nom',"like", "%".$search."%")
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }   else if($search != "" && $categorie == "all" && $class != "all"){
            $documents = Document::
                where('nom',"like", "%".$search."%")
                ->where('classes_id', $class)
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }   else if($search != "" && $categorie != "all" && $class == "all"){
            $documents = Document::
                where('nom',"like", "%".$search."%")
                ->where('document_categories_id', $categorie)
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }   else if($search != "" && $categorie != "all" && $class != "all"){
            $documents = Document::
                where('nom',"like", "%".$search."%")
                ->where('document_categories_id', $categorie)
                ->where('classes_id', $class)
                ->with('class')
                ->with('categorie')
                ->orderBy('created_at', 'desc')
                ->paginate(5);
        }
        if ($documents->isEmpty()) {
            return response()->json([
                'message' => 'Aucun document trouvé'
            ], 404);
        } else {
            return response()->json(
                $documents
            , 200);
        }
    }
}
