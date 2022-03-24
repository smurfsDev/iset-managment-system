<?php

namespace App\Http\Controllers;

use App\Http\Requests\HeadersRequest;
use App\Models\header;
use Illuminate\Http\Request;

class HeadersController extends Controller
{
    public function getHeaders($id){

        $headers = Header::where('idClub','=',$id)->orderBy('updated_at')->paginate(5);
        if (sizeof($headers) > 0)
            return response()->json(
                $headers,
                200
            );
        else
            return response()->json([
                "Aucun header"
            ], 404);
    }
    public function createHeader($id, HeadersRequest $request){
        $petiteDescription = $request->input('petiteDescription');
        $backgroudImage	 = $request->input('backgroudImage');
        $idClub = $id;

        $newHeader = array(
            "petiteDescription" => $petiteDescription,
            "backgroudImage" => $backgroudImage,
            "idClub" => $idClub
        );

        $createNewHeader = Header::create($newHeader);

        if ($createNewHeader) {
            return response()->json([
                'data' => [
                    'message' => 'Header created',
                    'id' => $createNewHeader->id,
                    'attributes' => $createNewHeader
                ]
            ], 201);
        }
        return "Header created";
    }

    public function deleteHeader($idClub,$idHeader){
      
        $header = Header::find($idHeader);
       
        if ($header){
            $header->delete();
            return response()->json([
                'type' => 'Header',
                'message' => 'Header Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Header',
                'message' => 'Header non trouvée'
            ], 404);
        }
    }

    public function updateHeader($idClub, $idHeader, HeadersRequest $request){

        $header = Header::find($idHeader);
        if ($header) {
      
            $header->petiteDescription = $request->input('petiteDescription') ? $request->input('petiteDescription') : $header->petiteDescription;
            $header->backgroudImage = $request->input('backgroudImage') ? $request->input('backgroudImage') : $header->backgroudImage;
            $header->idClub = $idClub;
            $header->save();
            return response()->json([
                'message' => 'Header mis à jour',
                'id' => $header->id,
                'attributes' => $header
            ], 201);
        } else {
            return response()->json([
                "Header non trouvée"
            ], 404);
        }
    }

}
