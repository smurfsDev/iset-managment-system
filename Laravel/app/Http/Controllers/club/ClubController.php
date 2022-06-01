<?php

namespace App\Http\Controllers\club;

use App\Http\Controllers\Controller;
use App\Http\Requests\DemandeAdhesionClubRequest;
use App\Models\Member;
use App\Models\club;
use App\Models\DemandeAdhesionClub;
use Illuminate\Http\Exceptions\HttpResponseException;
use Illuminate\Http\Request;

class ClubController extends Controller
{

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function show()
    {
        // return club with demandeCreationClub where demandeCreationClub's status=1
        $Clubs = club::whereHas('demandeCreationClub', function ($query) {
            $query->where('status', 1);
        })->with('demandeCreationClub')->paginate(5);
        if (empty($Clubs)) {
            return response()->json(['message' => 'No clubs found'], 404);
        } else {
            return response()->json($Clubs, 200);
        }
    }

    /**
     * get club with id
     * @param Integer $id
     * @return \Illuminate\Http\Response
     */
    public function getClub($id)
    {
        $club = club::find($id);
        if (empty($club)) {
            return response()->json(['message' => 'No club found'], 404);
        } else {
            return response()->json($club, 200);
        }
    }

    /**
     * create new demandeAdhesionClub
     * @param DemandeAdhesionClubRequest $request
     * @return \Illuminate\Http\Response
     */
    public function DemandeeAdhesion(DemandeAdhesionClubRequest $request)
    {
        $user_id = $request->user_id;
        $club_id = $request->input('club_id');
        $message = $request->input('message');
        $status = 0;
        $demandeAdhesionClub = DemandeAdhesionClub::where('user_id', $user_id)->where('club_id', $club_id)->first();

        if (empty($demandeAdhesionClub)) {
            $demande = new DemandeAdhesionClub();
            $demande->user_id = $user_id;
            $demande->club_id = $club_id;
            $demande->message = $message;
            $demande->status = $status;
            $demande->save();
            return response()->json(['success' => 'Demande envoyée avec succès']);
        } else {
            throw new HttpResponseException(response()->json([
                'success'   => false,
                'message'   => 'Vous avez déjà envoyé une demande ',
            ],400));
        }
    }

    /**
     * Get all demandeAdhesionClub of auth users' club
     * @param Request $request
     * @return \Illuminate\Http\Response
     */
    public function getDemandeAdhesion(Request $request)
    {
        $demandes = $request->user()->DemandeAdhesionClub()->with('club.demandeCreationClub')->paginate(5);
        if (empty($demandes)) {
            return response()->json(['message' => 'No demande found'], 404);
        } else {
            return response()->json($demandes, 200);
        }
    }

    /**
     * delete demandeAdhesionClub
     * @param Request $request
     * @return \Illuminate\Http\Response
     */
    public function deleteDemandeAdhesion(Request $request)
    {
        $demande = $request->user()->DemandeAdhesionClub()->find($request->id);
        if (empty($demande)) {
            return response()->json(['message' => 'No demande found'], 404);
        } else {
            $demande->delete();
            return response()->json(['success' => 'Demande supprimée avec succès']);
        }
    }

    /**
     * get all demande adhesion by club
     * @param Request $request
     * @return \Illuminate\Http\Response
     */
    public function getDemandeAdhesionByClub(Request $request)
    {
        $demandes = $request->user()->club()->with('DemandeAdhesionClub')->with('DemandeAdhesionClub.user')->paginate(5);
        return $demandes;
        if (empty($demandes)) {
            return response()->json(['message' => 'No demande found'], 404);
        } else {
            return response()->json($demandes, 200);
        }
    }

    /**
     * accept demandeAdhesionClub
     * @param Request $request
     * @return \Illuminate\Http\Response
     */
    public function acceptDemandeAdhesion(Request $request, $id)
    {
        $demande = DemandeAdhesionClub::find($id);
        if (empty($demande)) {
            return response()->json(['message' => 'No demande found'], 404);
        } else {
            Member::create([
                "user_id" => $demande->user_id,
                "club_id" => $demande->club_id
            ]);
            $demande->status = 1;
            $demande->admin_id = $request->user()->id;
            $demande->save();
            return response()->json(['success' => 'Demande acceptée avec succès']);
        }
    }

    /**
     * refuse demandeAdhesionClub
     * @param Request $request
     * @return \Illuminate\Http\Response
     */
    public function refuserDemandeAdhesion(Request $request, $id)
    {
        $demande = DemandeAdhesionClub::find($id);
        if (empty($demande)) {
            return response()->json(['message' => 'No demande found'], 404);
        } else {
            $demande->status = 2;
            $demande->admin_id = $request->user()->id;
            $demande->save();
            $membre = Member::where('user_id', '=', $demande->user_id)->where('club_id', '=', $demande->club_id);
            $membre->delete();
            return response()->json(['success' => 'Demande refuser']);
        }
    }
}
