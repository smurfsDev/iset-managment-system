<?php

namespace App\Http\Controllers\club\pageClub;

use App\Http\Controllers\Controller;
use App\Http\Requests\HeadersRequest;
use App\Models\header;
use Illuminate\Http\Request;

class HeadersController extends Controller
{
    public function getHeaders(Request $request)
    {
        $club = $request->user()->club()->get('id');
        $headers = Header::where('idClub', '=', $club[0]->id)->orderBy('updated_at')->paginate(5);
        if (sizeof($headers) > 0)
            return response()->json(
                $headers,
                200
            );
        else
            return response()->json([
                null
            ], 200);
    }
    public function createHeader(HeadersRequest $request)
    {
        $club = $request->user()->club()->get('id');

        $header = Header::where('id', $club[0]->id)->first();
        if (empty($header)) {

            $petiteDescription = $request->input('petiteDescription');
            $backgroudImage     = $request->input('backgroudImage');
            $idClub = $club[0]->id;

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
        }
        return "Header exist";
    }

    public function deleteHeader($idHeader)
    {

        $header = Header::find($idHeader);

        if ($header) {
            $header->delete();
            return response()->json([
                'type' => 'Header',
                'message' => 'Header Supprimé! '
            ], 204);
        } else {
            return response()->json([
                'type' => 'Header',
                'message' => 'Header non trouvée'
            ], 404);
        }
    }

    public function updateHeader($idHeader, HeadersRequest $request)
    {
        $header = Header::find($idHeader);
        if ($header) {

            $header->petiteDescription = $request->input('petiteDescription') ? $request->input('petiteDescription') : $header->petiteDescription;
            $header->backgroudImage = $request->input('backgroudImage') ? $request->input('backgroudImage') : $header->backgroudImage;
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
