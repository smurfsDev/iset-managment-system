<?php

namespace App\Http\Controllers;

use App\Models\Member;
use Illuminate\Http\Request;

class MemberController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function getMembers()
    {
        $members = Member::with('user')->with('club')->paginate(5);
        if (!empty($members)) {
            $response = [
                'success' => true,
                'data' => $members,
                'message' => 'List of members retrieved successfully.'
            ];
            return response()->json($response, 200);
        } else {
            $response = [
                'success' => false,
                'data' => 'Empty',
                'message' => 'List of members could not be retrieved.'
            ];
            return response()->json($response, 404);
        }	
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function deleteMember($id)
    {
        $member = Member::find($id);
        if (!empty($member)) {
            $member->delete();
            $response = [
                'success' => true,
                'data' => $member,
                'message' => 'Member deleted successfully.'
            ];
            return response()->json($response, 200);
        } else {
            $response = [
                'success' => false,
                'data' => 'Empty',
                'message' => 'Member could not be deleted.'
            ];
            return response()->json($response, 404);
        }
    }
}
