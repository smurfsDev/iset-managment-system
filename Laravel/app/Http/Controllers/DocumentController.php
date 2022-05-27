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
        $class = $request->input('classes_id');
        $categorie = $request->input('document_categories_id');
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
            $documents->classes_id = $request->input('classes_id') ? $request->input('classes_id') : $documents->class;
            $documents->file = $request->input('file') ? $request->input('file') : $documents->file;
            $documents->document_categories_id = $request->input('document_categories_id') ? $request->input('document_categories_id') : $documents->categorie;

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


    public function search(Request $request)
    {

        $search = $request->search??"";
        $categorie = $request->categorie??"";
        $class = $request->class??"";
        $documents =[];
        if($search==""&&$categorie=="all"&&$class=="all"){
            $documents = Document::paginate(5);
        }   
        else if($categorie=="all"){
            if ($search!=""){
                //with('classe')->with('categorie')->

                $documents = Document::where('nom', 'like', '%' . $search . '%')
                ->where('classes_id', 'like', '%' . $class . '%')
                ->orderBy('updated_at', 'desc')
                ->paginate(5);
            }else{
                $documents = Document::where('classes_id', '=', $class )
                    ->orderBy('updated_at', 'desc')
                    ->paginate(5);
            }
        }else{
            if ($search!=""){
                if ($class=="all"){
                    $documents = Document::where('nom', 'like', '%' . $search . '%')
                        ->where('document_categories_id', '=', $categorie )
                        ->orderBy('updated_at', 'desc')
                        ->paginate(5);
                }else{
                    $documents = Document::where('nom', 'like', '%' . $search . '%')
                        ->where('document_categories_id', '=', $categorie )
                        ->where('classes_id', '=',  $class )
                        ->orderBy('updated_at', 'desc')
                        ->paginate(5);
                }
            }else{
                if ($class=="all"){
                    $documents = Document::where('document_categories_id', 'like', '%' . $categorie . '%')
                        ->orderBy('updated_at', 'desc')
                        ->paginate(5);
                }else{
                    $documents = Document::where('document_categories_id', 'like', '%' . $categorie . '%')
                        ->where('classes_id', 'like', '%' . $class . '%')
                        ->orderBy('updated_at', 'desc')
                        ->paginate(5);
                }
            }

        }

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
}
